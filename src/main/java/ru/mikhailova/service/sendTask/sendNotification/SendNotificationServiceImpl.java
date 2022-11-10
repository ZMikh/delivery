package ru.mikhailova.service.sendTask.sendNotification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.repository.DeliveryRepository;
// TODO replace entity with dto
@Service
@RequiredArgsConstructor
@Slf4j
public class SendNotificationServiceImpl implements SendNotificationService {
    private final DeliveryRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional(readOnly = true)
    @Override
    public void sendNotification(Long id) {
        Delivery delivery = repository.findById(id).orElseThrow();
        log.info("notification is sent");
        kafkaTemplate.send("notification", delivery);
    }
}
