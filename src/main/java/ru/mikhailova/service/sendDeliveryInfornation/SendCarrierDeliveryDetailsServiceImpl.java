package ru.mikhailova.service.sendDeliveryInfornation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.repository.DeliveryRepository;

@Service
@Slf4j
public class SendCarrierDeliveryDetailsServiceImpl implements SendCarrierDeliveryDetailsService {
    private final DeliveryRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String notificationTopic;

    public SendCarrierDeliveryDetailsServiceImpl(DeliveryRepository repository,
                                                 KafkaTemplate<String, Object> kafkaTemplate,
                                                 @Value("${kafka.topic.notification}") String notificationTopic) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.notificationTopic = notificationTopic;
    }

    @Transactional(readOnly = true)
    @Override
    public void sendDeliveryInformation(Long id) {
        Delivery delivery = repository.findById(id).orElseThrow();
        log.info("Delivery information with id: {} is sent", delivery.getId());
        kafkaTemplate.send(notificationTopic, delivery);
    }
}

