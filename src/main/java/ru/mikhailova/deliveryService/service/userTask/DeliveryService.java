package ru.mikhailova.deliveryService.service.userTask;

import ru.mikhailova.deliveryService.domain.Delivery;

import java.util.List;

/**
 * Сервис доставки
 */
public interface DeliveryService {
    /**
     * Создать доставку
     *
     * @param delivery сущность Доставка
     * @return сущность Доставка
     */
    Delivery createDelivery(Delivery delivery);

    /**
     * Получить доставку по идентификатору
     *
     * @param id идентификатор доставки
     * @return сущность Доставка
     */
    Delivery getDeliveryById(Long id);

    /**
     * Получить все доставки
     *
     * @return список доставок
     */
    List<Delivery> getAllDeliveries();

    /**
     * Обновить параметры доставки по идентификатору
     *
     * @param id                 идентификатор доставки
     * @param deliveryUpdateInfo обновленные параметры доставки
     * @return сущность Доставка
     */
    Delivery updateDeliveryById(Long id, DeliveryUpdateInfo deliveryUpdateInfo);

    /**
     * Удалить доставку по идентификатору
     *
     * @param id идентификатор доставки
     */
    void deleteDeliveryById(Long id);

    /**
     * Подтвердить заказ-доставку по идентификатору
     *
     * @param id идентификатор доставки
     * @return сущность Доставка
     */
    Delivery confirmDelivery(Long id, DeliveryConfirmInfo deliveryConfirmInfo);

    /**
     * Выдать клиенту заказ самовывозом по идентификатору
     *
     * @param id идентификатор доставки
     * @return сущность Доставка
     */
    Delivery pickUpDelivery(Long id);
}
