package ru.mikhailova.deliveryService.domain;

/**
 * Статусы доставки
 */
public enum DeliveryState {
    /**
     * Оформить заказ
     */
    NEW,
    /**
     * Заказ принят в обработку
     */
    IN_PROCESSING,
    /**
     * Заказ оформлен
     */
    REGISTERED,
    /**
     * Заказ оплачен
     */
    PAID,
    /**
     * Заказ выполнен
     */
    FINISHED,
    /**
     * Заказ отменен по тех причинам
     */
    TECH_ERROR,
    /**
     * Клиент отменил заказ
     */
    CLIENT_CANCELLATION,
    /**
     * Неудачная попытка оплаты
     */
    PAYMENT_ERROR
}
