package ru.mikhailova.service.sendNotification;

/**
 * Отправка уведомления
 */
public interface SendNotificationService {
    /**
     * Отправка уведомления
     * @param id идентификатор доставки
     */
    void sendNotification(Long id);
}
