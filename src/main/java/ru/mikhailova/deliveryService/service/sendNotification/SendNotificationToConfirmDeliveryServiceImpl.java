package ru.mikhailova.deliveryService.service.sendNotification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.deliveryService.domain.Delivery;
import ru.mikhailova.deliveryService.repository.DeliveryRepository;

@Service
@Slf4j
public class SendNotificationToConfirmDeliveryServiceImpl implements SendNotificationToConfirmDeliveryService {
    private final DeliveryRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String notificationTopic;

    public SendNotificationToConfirmDeliveryServiceImpl(DeliveryRepository repository,
                                                        KafkaTemplate<String, Object> kafkaTemplate,
                                                        @Value("${kafka.topic.notification}") String notificationTopic) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.notificationTopic = notificationTopic;
    }


    @Transactional(readOnly = true)
    @Override
    public void sendNotification(Long id) {
        Delivery delivery = repository.findById(id).orElseThrow();
        log.info("Notification is sent");
        kafkaTemplate.send(notificationTopic, delivery);
    }
}
