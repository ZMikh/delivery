package ru.mikhailova.deliveryService.domain;

/**
 * Статусы подтверждения заказа
 */
public enum ConfirmState {
    /**
     * Заказ отменен
     */
    CONFIRMED,
    /**
     * Заказ подтвержден
     */
    CANCELLED
}
