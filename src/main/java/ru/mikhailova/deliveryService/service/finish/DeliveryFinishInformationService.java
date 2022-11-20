package ru.mikhailova.deliveryService.service.finish;

/**
 * Получение информации о завершении доставки
 */
public interface DeliveryFinishInformationService {
    /**
     * Получение сообщения
     * @param id идентификатор доставки
     */
    void sendMessageTask(Long id);
}
