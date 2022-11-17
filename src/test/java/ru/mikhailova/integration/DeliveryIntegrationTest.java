package ru.mikhailova.integration;


import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;
import ru.mikhailova.controller.dto.*;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.domain.DeliveryState;
import ru.mikhailova.listener.dto.DeliveryFinishDto;
import ru.mikhailova.listener.dto.DeliveryMessageDto;
import ru.mikhailova.repository.DeliveryRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class DeliveryIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private DeliveryRepository repository;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @MockBean
    protected RestTemplate restTemplate;
    private Delivery delivery;
    private TypeReference<List<DeliveryResponseDto>> listTypeReference = new TypeReference<List<DeliveryResponseDto>>() {
    };
    private CartResponseDtoList carts;

    @Value("${kafka.topic.cancel-message}")
    private String cancelMessageTopic;
    @Value("${kafka.topic.delivery-finish-message}")
    private String deliveryFinishMessageTopic;

    @BeforeEach
    void setUp() throws Exception {
        String jsonString = getJsonString("/json/cart.json");
        carts = objectMapper.readValue(jsonString, CartResponseDtoList.class);
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity<>(carts, HttpStatus.OK));

        DeliveryRequestCreateDto dto = new DeliveryRequestCreateDto();
        dto.setDescription("dish");
        DeliveryResponseDto responseDto = performCreateDelivery(dto, DeliveryResponseDto.class);
        delivery = repository.findById(responseDto.getId()).orElseThrow();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void couldCreateDelivery() {
        assertThat(delivery.getState()).isEqualTo(DeliveryState.IN_PROCESSING);
    }

    @Test
    void couldCheckDeliveryConfirmationUserTask() throws Exception {
        DeliveryRequestConfirmDto dto = new DeliveryRequestConfirmDto();
        dto.setConfirmationState("CONFIRMED");
        dto.setIsPickUp(false);

        DeliveryResponseDto responseDto = performConfirmDelivery(delivery.getId(), dto, DeliveryResponseDto.class);

        assertThat(responseDto.getState()).isEqualTo(DeliveryState.PAID.toString());
    }

    @Test
    void couldCheckDeliveryCancellationUserTask() throws Exception {
        DeliveryRequestConfirmDto dto = new DeliveryRequestConfirmDto();
        dto.setConfirmationState("CANCELLED");
        dto.setIsPickUp(false);

        DeliveryResponseDto responseDto = performConfirmDelivery(delivery.getId(), dto, DeliveryResponseDto.class);

        assertThat(responseDto.getState()).isEqualTo(DeliveryState.CLIENT_CANCELLATION.toString());
    }

    @Test
    void couldCheckDeliveryIsPickedUpUserTask() throws Exception {
        DeliveryRequestConfirmDto dto = new DeliveryRequestConfirmDto();
        dto.setConfirmationState("CONFIRMED");
        dto.setIsPickUp(true);
        performConfirmDelivery(delivery.getId(), dto, DeliveryResponseDto.class);


        DeliveryResponseDto responseDto = performPickUpDelivery(delivery.getId(), DeliveryResponseDto.class);
        assertThat(responseDto.getState()).isEqualTo(DeliveryState.FINISHED.toString());
    }

    @Test
    void checkCompensationServiceTaskTechErrorState() throws Exception {
        DeliveryMessageDto deliveryMessageDto = new DeliveryMessageDto();
        deliveryMessageDto.setId(delivery.getId());

        kafkaTemplate.send(cancelMessageTopic, deliveryMessageDto);
        Thread.sleep(1000);
        Delivery cancelledDelivery = repository.findById(delivery.getId()).orElseThrow();

        assertThat(cancelledDelivery.getState()).isEqualTo(DeliveryState.TECH_ERROR);
    }

    @Test
    void couldGetDeliveryById() throws Exception {
        DeliveryResponseDto dto = performGetDeliveryById(delivery.getId(), DeliveryResponseDto.class);

        assertThat(dto.getDescription()).isEqualTo("dish");
    }

    @Test
    void couldDeleteDeliveryById() throws Exception {
        performDeleteDeliveryById(delivery.getId());

        assertThat(repository.existsById(delivery.getId())).isFalse();
    }

    @Test
    void couldGetAllDeliveries() throws Exception {
        List<DeliveryResponseDto> dtoList = performGetAllDeliveries(listTypeReference);

        assertThat(dtoList.size()).isNotZero();
    }

    @Test
    void couldUpdateDeliveryById() throws Exception {
        DeliveryRequestUpdateDto dto = new DeliveryRequestUpdateDto();
        dto.setDeliveryTime(LocalDateTime.of(2030, 1, 1, 1, 1, 1));

        DeliveryResponseDto responseDto = performUpdateDeliveryById(delivery.getId(), dto, DeliveryResponseDto.class);

        assertThat(responseDto.getDeliveryTime()).isEqualTo(LocalDateTime.of(2030, 1, 1, 1, 1, 1));
    }

    @Test
    void couldCheckReceiveMessageOfDeliveryFinish() throws Exception {
        DeliveryRequestConfirmDto dto = new DeliveryRequestConfirmDto();
        dto.setConfirmationState("CONFIRMED");
        dto.setIsPickUp(false);
        performConfirmDelivery(delivery.getId(), dto, DeliveryResponseDto.class);

        DeliveryFinishDto deliveryFinishDto = new DeliveryFinishDto();
        deliveryFinishDto.setId(delivery.getId());

        kafkaTemplate.send(deliveryFinishMessageTopic, deliveryFinishDto);
        Thread.sleep(1000);
        Delivery finishedDelivery = repository.findById(delivery.getId()).orElseThrow();

        assertThat(finishedDelivery.getState()).isEqualTo(DeliveryState.FINISHED);
    }

    @Test
    void couldCheckCartServiceTask() {
        Delivery currentDelivery = repository.findById(delivery.getId()).orElseThrow();
        assertThat(currentDelivery.getCarts().size()).isEqualTo(2);
    }

    @Test
    void couldCheckPaymentServiceTaskError() throws Exception {
        DeliveryRequestConfirmDto dto = new DeliveryRequestConfirmDto();
        dto.setConfirmationState("CONFIRMED");
        dto.setDescription("some error");
        dto.setIsPickUp(false);

        DeliveryResponseDto responseDto = performConfirmDelivery(delivery.getId(), dto, DeliveryResponseDto.class);

        assertThat(responseDto.getState()).isEqualTo(DeliveryState.PAYMENT_ERROR.toString());
    }
}