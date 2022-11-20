package ru.mikhailova.deliveryService.service.changeState;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.deliveryService.domain.Delivery;
import ru.mikhailova.deliveryService.domain.DeliveryState;
import ru.mikhailova.deliveryService.repository.DeliveryRepository;

@Service
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
