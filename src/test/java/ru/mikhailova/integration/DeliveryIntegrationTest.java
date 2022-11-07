package ru.mikhailova.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.domain.DeliveryState;
import ru.mikhailova.dto.DeliveryRequestConfirmDto;
import ru.mikhailova.dto.DeliveryRequestCreateDto;
import ru.mikhailova.dto.DeliveryRequestUpdateDto;
import ru.mikhailova.dto.DeliveryResponseDto;
import ru.mikhailova.repository.DeliveryRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private DeliveryRepository repository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

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
    void couldCreateDelivery() throws Exception {
        assertThat(delivery.getState()).isEqualTo(DeliveryState.IN_PROCESSING);
    }

    @Test
    void couldCheckDeliveryConfirmationUserTask() throws Exception {
        DeliveryRequestConfirmDto dto = new DeliveryRequestConfirmDto();
        dto.setState("CONFIRMED");
        DeliveryResponseDto responseDto = performConfirmDelivery(delivery.getId(), dto, DeliveryResponseDto.class);
        assertThat(responseDto.getState()).isEqualTo(DeliveryState.PAID.toString());
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
    @Disabled
    void couldUpdateDeliveryById() throws Exception {
        DeliveryRequestUpdateDto dto = new DeliveryRequestUpdateDto();

        DeliveryResponseDto responseDto = performUpdateDeliveryById(delivery.getId(), dto, DeliveryResponseDto.class);

        assertThat(responseDto.getState()).isEqualTo(DeliveryState.PAID);
    }
}