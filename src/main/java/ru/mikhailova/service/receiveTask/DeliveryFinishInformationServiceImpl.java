package ru.mikhailova.service.receiveTask;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.repository.DeliveryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryFinishInformationServiceImpl implements DeliveryFinishInformationService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final DeliveryRepository repository;

    @Transactional(readOnly = true)
    @Override
    public void sendMessageTask(Long id) {
        Delivery delivery = repository.findById(id).orElseThrow();
        log.info("delivery finish information with id: {} is sent", delivery.getId());
        kafkaTemplate.send("deliveryFinishMessage", delivery);
    }
}
