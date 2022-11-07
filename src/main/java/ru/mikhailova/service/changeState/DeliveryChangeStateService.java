package ru.mikhailova.service.changeState;

import ru.mikhailova.domain.DeliveryState;

public interface DeliveryChangeStateService {
    void changeDeliveryState(DeliveryState deliveryState, Long id);
}
