package ru.mikhailova.deliveryService.service.sendNotification;

/**
 * Отправка уведомления
 */
public interface SendNotificationToConfirmDeliveryService {
    /**
     * Отправка уведомления
     * @param id идентификатор доставки
     */
    void sendNotification(Long id);
}
