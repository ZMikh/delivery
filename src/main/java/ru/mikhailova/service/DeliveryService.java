package ru.mikhailova.service;

import ru.mikhailova.domain.Delivery;

import java.util.List;

/**
 * Сервис доставки
 */
public interface DeliveryService {
    /**
     * Создать доставки
     *
     * @param delivery сущность Доставка
     * @return сущность Доставка
     */
    Delivery createDelivery(Delivery delivery);

    /**
     * Получить доставки по идентификатору доставки
     *
     * @param id идентификатор доставки
     * @return сущность Доставка
     */
    Delivery getDeliveryById(Long id);

    /**
     * Получить все доставок
     *
     * @return список доставок
     */
    List<Delivery> getAllDeliveries();

    /**
     * Обновить параметры доставки
     *
     * @param id идентификатор доставки
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
}
