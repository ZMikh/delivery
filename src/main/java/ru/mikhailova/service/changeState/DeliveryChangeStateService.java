package ru.mikhailova.service.changeState;

import ru.mikhailova.domain.DeliveryState;

/**
 * Выполнение переходов между событиями Camunda
 */
public interface DeliveryChangeStateService {
    /**
     * Сменить событие
     * @param deliveryState статус доставки
     * @param id идентификатор доставик
     */
    void changeDeliveryState(DeliveryState deliveryState, Long id);
}
