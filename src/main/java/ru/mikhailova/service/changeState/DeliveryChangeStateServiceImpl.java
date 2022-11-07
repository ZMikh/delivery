package ru.mikhailova.service.changeState;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.domain.DeliveryState;
import ru.mikhailova.repository.DeliveryRepository;

@Component
@RequiredArgsConstructor
public class DeliveryChangeStateServiceImpl implements DeliveryChangeStateService {
    private final DeliveryRepository repository;

    @Transactional
    @Override
    public void changeDeliveryState(DeliveryState deliveryState, Long id) {
        Delivery delivery = repository.findById(id).orElseThrow();
        delivery.setState(deliveryState);
        repository.save(delivery);
    }
}
