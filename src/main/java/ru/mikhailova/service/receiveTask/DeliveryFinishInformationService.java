package ru.mikhailova.service.receiveTask;

/**
 * Сервисная задача получения информации о завершении доставки
 */
public interface DeliveryFinishInformationService {
    /**
     * Выполнение действия "Получение сообщения"
     * @param id идентификатор доставки
     */
    void sendMessageTask(Long id);
}
