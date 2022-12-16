package ru.mikhailova.deliveryService.service.changeState;

import ru.mikhailova.deliveryService.domain.DeliveryState;

/**
 * Выполнение переходов между событиями Camunda
 */
public interface DeliveryChangeStateService {
    /**
     * Сменить событие
     * @param deliveryState статус доставки
     * @param id идентификатор доставки
     */
    void changeDeliveryState(DeliveryState deliveryState, Long id);
}
