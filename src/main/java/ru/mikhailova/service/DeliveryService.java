package ru.mikhailova.service;

import ru.mikhailova.domain.Delivery;

import java.util.List;

public interface DeliveryService {
    Delivery createDelivery(Delivery delivery);
    Delivery getDeliveryById(Long id);
    List<Delivery> getAllDeliveries();
    Delivery updateDeliveryById(Long id, UpdateDeliveryInfo updateDeliveryInfo);
    void deleteDeliveryById(Long id);
}
