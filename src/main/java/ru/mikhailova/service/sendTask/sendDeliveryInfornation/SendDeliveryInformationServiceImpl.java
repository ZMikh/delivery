package ru.mikhailova.service.sendTask.sendDeliveryInfornation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.repository.DeliveryRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendDeliveryInformationServiceImpl implements SendDeliveryInformationService {
    private final DeliveryRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional(readOnly = true)
    @Override
    public void sendDeliveryInformation(Long id) {
        Delivery delivery = repository.findById(id).orElseThrow();
        log.info("delivery information with id: {} is sent", delivery.getId());
        kafkaTemplate.send("deliveryInformation", delivery);
    }
}
