package ru.mikhailova.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.domain.DeliveryState;
import ru.mikhailova.dto.DeliveryRequestConfirmDto;
import ru.mikhailova.dto.DeliveryRequestCreateDto;
import ru.mikhailova.dto.DeliveryRequestUpdateDto;
import ru.mikhailova.dto.DeliveryResponseDto;
import ru.mikhailova.listener.DeliveryFinishDto;
import ru.mikhailova.listener.DeliveryMessageDto;
import ru.mikhailova.repository.DeliveryRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private DeliveryRepository repository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private ConsumerFactory<String, JsonNode> consumerFactory;

    private Delivery delivery;

    private TypeReference<List<DeliveryResponseDto>> listTypeReference = new TypeReference<List<DeliveryResponseDto>>() {
    };

    @BeforeEach
    void setUp() throws Exception {
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
    void couldCheckSendNotificationDelivery() throws Exception {
        try (Consumer<String, JsonNode> consumer = consumerFactory.createConsumer()) {
            consumer.subscribe(List.of("notification"));
            ConsumerRecord<String, JsonNode> record = KafkaTestUtils.getSingleRecord(consumer, "notification", 20000);
            assertThat(record.value()).isNotEmpty();
        }
    }

    @Test
    void couldCheckDeliveryConfirmationUserTask() throws Exception {
        DeliveryRequestConfirmDto dto = new DeliveryRequestConfirmDto();
        dto.setState("CONFIRMED");

        DeliveryResponseDto responseDto = performConfirmDelivery(delivery.getId(), dto, DeliveryResponseDto.class);

        assertThat(responseDto.getState()).isEqualTo(DeliveryState.PAID.toString());
    }

    @Test
    void couldCheckDeliveryCancellationUserTask() throws Exception {
        DeliveryRequestConfirmDto dto = new DeliveryRequestConfirmDto();
        dto.setState("CANCELLED");

        DeliveryResponseDto responseDto = performConfirmDelivery(delivery.getId(), dto, DeliveryResponseDto.class);

        assertThat(responseDto.getState()).isEqualTo(DeliveryState.CLIENT_CANCELLATION.toString());
    }

    @Test
    void couldCheckDeliveryIsPickedUpUserTask() throws Exception {
        DeliveryRequestConfirmDto dto = new DeliveryRequestConfirmDto();
        dto.setState("CONFIRMED");
        dto.setIsPickUp(true);
        performConfirmDelivery(delivery.getId(), dto, DeliveryResponseDto.class);


        DeliveryResponseDto responseDto = performPickUpDelivery(delivery.getId(), DeliveryResponseDto.class);
        assertThat(responseDto.getState()).isEqualTo(DeliveryState.FINISHED.toString());
    }

    @Test
    void checkCompensationServiceTaskTechErrorState() throws Exception {
        DeliveryMessageDto deliveryMessageDto = new DeliveryMessageDto();
        deliveryMessageDto.setId(delivery.getId());

        kafkaTemplate.send("cancelMessage", deliveryMessageDto);
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
    void couldCheckSendDeliveryInformation() throws Exception {
        try (Consumer<String, JsonNode> consumer = consumerFactory.createConsumer()) {
            consumer.subscribe(List.of("deliveryInformation"));

            DeliveryRequestConfirmDto dto = new DeliveryRequestConfirmDto();
            dto.setState("CONFIRMED");
            dto.setIsPickUp(false);

            performConfirmDelivery(delivery.getId(), dto, DeliveryResponseDto.class);

            ConsumerRecord<String, JsonNode> record = KafkaTestUtils.getSingleRecord(consumer, "deliveryInformation", 2000);
            assertThat(record.value()).isNotEmpty();
        }
    }

    @Test
    void couldCheckReceiveMessageOfDeliveryFinish() throws Exception {
        DeliveryRequestConfirmDto dto = new DeliveryRequestConfirmDto();
        dto.setState("CONFIRMED");
        dto.setIsPickUp(false);
        performConfirmDelivery(delivery.getId(), dto, DeliveryResponseDto.class);

        DeliveryFinishDto deliveryFinishDto = new DeliveryFinishDto();
        deliveryFinishDto.setId(delivery.getId());

        kafkaTemplate.send("deliveryFinishMessage", deliveryFinishDto);
        Thread.sleep(1000);
        Delivery finishedDelivery = repository.findById(delivery.getId()).orElseThrow();

        assertThat(finishedDelivery.getState()).isEqualTo(DeliveryState.FINISHED);
    }
}