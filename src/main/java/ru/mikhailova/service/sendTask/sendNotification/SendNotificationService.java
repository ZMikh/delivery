package ru.mikhailova.service.sendTask.sendNotification;

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
