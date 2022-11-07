package ru.mikhailova.service;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.domain.DeliveryState;
import ru.mikhailova.repository.DeliveryRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceImplTest {
    @Mock
    private DeliveryRepository repository;

    @Mock
    private RuntimeService runtimeService;

    @Mock
    private TaskService taskService;

    private DeliveryServiceImpl service;

    private Delivery delivery;

    @Captor
    private ArgumentCaptor<Delivery> argumentCaptor;

    @BeforeEach
    void setUp() {
        delivery = Delivery.builder()
                .id(1L)
                .state(DeliveryState.NEW)
                .deliveryTime(LocalDateTime.of(2022, 11, 5, 8, 0, 0))
                .description("dish")
                .build();

        service = new DeliveryServiceImpl(taskService, repository, runtimeService);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateDelivery() {
        when(repository.save(any())).thenReturn(delivery);

        service.createDelivery(delivery);

        verify(repository, times(1)).save(delivery);
    }

    @Test
    void shouldGetDeliveryById() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(delivery));

        service.getDeliveryById(any());

        verify(repository, times(1)).findById(any());
    }

    @Test
    void shouldGetAllDeliveries() {
        service.getAllDeliveries();

        verify(repository).findAll();
    }

    @Test
    void shouldUpdateDeliveryById() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(delivery));
        DeliveryUpdateInfo deliveryUpdateInfo = DeliveryUpdateInfo.builder()
                .deliveryTime(LocalDateTime.of(2022, 1, 1,1,1,1))
                .build();

        service.updateDeliveryById(1L, deliveryUpdateInfo);

        verify(repository).save(argumentCaptor.capture());
        Delivery capturedDelivery = argumentCaptor.getValue();
        assertThat(capturedDelivery.getDeliveryTime()).isEqualTo(deliveryUpdateInfo.getDeliveryTime());
    }

    @Test
    void shouldDeleteDeliveryById() {
        service.deleteDeliveryById(any());

        verify(repository, atLeastOnce()).deleteById(any());
    }
}