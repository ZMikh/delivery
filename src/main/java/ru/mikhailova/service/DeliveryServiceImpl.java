package ru.mikhailova.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.repository.DeliveryRepository;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository repository;
    private final RuntimeService runtimeService;

    @Transactional
    @Override
    public Delivery createDelivery(Delivery delivery) {
        Delivery savedDelivery = repository.save(delivery);
        log.info("delivery with id: {} is created", savedDelivery.getId());
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", savedDelivery.getId());
        runtimeService.startProcessInstanceByKey("delivery", map);
        return savedDelivery;
    }

    @Transactional(readOnly = true)
    @Override
    public Delivery getDeliveryById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Delivery> getAllDeliveries() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Delivery updateDeliveryById(Long id, DeliveryUpdateInfo deliveryUpdateInfo) {
        Delivery delivery = repository.findById(id).orElseThrow();
        delivery.setDeliveryTime(deliveryUpdateInfo.getDeliveryTime());
        delivery.setState(deliveryUpdateInfo.getState());
        delivery.setDescription(deliveryUpdateInfo.getDescription());
        repository.save(delivery);
        log.info("delivery with id: {} is updated", id);
        return delivery;
    }

    @Transactional
    @Override
    public void deleteDeliveryById(Long id) {
        repository.deleteById(id);
        log.info("delivery with id: {} is deleted", id);
    }
}
