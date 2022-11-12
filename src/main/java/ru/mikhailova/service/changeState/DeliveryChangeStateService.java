package ru.mikhailova.service.changeState;

import ru.mikhailova.domain.DeliveryState;

/**
 * Сервис по смене событий Camunda
 */
public interface DeliveryChangeStateService {
    void changeDeliveryState(DeliveryState deliveryState, Long id);
}
