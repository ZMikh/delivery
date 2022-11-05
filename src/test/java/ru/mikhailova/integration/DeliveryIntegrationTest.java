package ru.mikhailova.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.domain.DeliveryState;
import ru.mikhailova.dto.DeliveryRequestCreateDto;
import ru.mikhailova.dto.DeliveryRequestUpdateDto;
import ru.mikhailova.dto.DeliveryResponseDto;
import ru.mikhailova.repository.DeliveryRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private DeliveryRepository repository;

    private Delivery delivery;

    private TypeReference<List<DeliveryResponseDto>> listTypeReference = new TypeReference<List<DeliveryResponseDto>>() {
    };


    @BeforeEach
    void setUp() {
        delivery = Delivery.builder()
                .deliveryTime(LocalDateTime.of(2022, 11, 5, 8, 0, 0))
                .description("dish")
                .build();

        repository.save(delivery);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void couldCreateDelivery() throws Exception {
        DeliveryRequestCreateDto deliveryRequestCreateDto = new DeliveryRequestCreateDto();
        deliveryRequestCreateDto.setDeliveryTime(LocalDateTime.now());

        DeliveryResponseDto dto = performCreateDelivery(deliveryRequestCreateDto, DeliveryResponseDto.class);

        assertThat(dto.getState()).isEqualTo(DeliveryState.NEW);
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
        dto.setState(DeliveryState.PAID);

        DeliveryResponseDto responseDto = performUpdateDeliveryById(delivery.getId(), dto, DeliveryResponseDto.class);

        assertThat(responseDto.getState()).isEqualTo(DeliveryState.PAID);
    }
}