package ru.mikhailova.service.sendDeliveryInfornation;

/**
 * Отправка информации по доставке
 */
public interface SendDeliveryInformationService {
    /**
     * Отправить клиенту информацию по доставке
     * @param id идентификатор доставки
     */
    void sendDeliveryInformation(Long id);
}
