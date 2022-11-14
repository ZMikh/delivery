package ru.mikhailova.service.userTask;

import ru.mikhailova.domain.Delivery;

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
     * Получить доставку по идентификатору доставки
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
     * Обновить параметры доставки по идентификатору доставки
     *
     * @param id                 идентификатор доставки
     * @param deliveryUpdateInfo обновленные параметры доставки
     * @return сущность Доставка
     */
    Delivery updateDeliveryById(Long id, DeliveryUpdateInfo deliveryUpdateInfo);

    /**
     * Удалить доставку по идентификатору доставки
     *
     * @param id идентификатор доставки
     */
    void deleteDeliveryById(Long id);

    /**
     * Подтвердить заказ-доставку по идентификатору доставки
     *
     * @param id идентификатор доставки
     * @return сущность Доставка
     */
    Delivery confirmDelivery(Long id, DeliveryConfirmInfo deliveryConfirmInfo);

    /**
     * Выдать клиенту заказ самовывозом по идентификатору доставки
     *
     * @param id идентификатор доставки
     * @return сущность Доставка
     */
    Delivery pickUpDelivery(Long id);
}
