package ru.mikhailova.deliveryService.service.sendDeliveryInfornation;

/**
 * Отправка информации по доставке
 */
public interface SendCarrierDeliveryDetailsService {
    /**
     * Отправить клиенту информацию по доставке
     * @param id идентификатор доставки
     */
    void sendDeliveryInformation(Long id);
}
