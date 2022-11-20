package ru.mikhailova.deliveryService.service.sendDeliveryInfornation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.deliveryService.domain.Delivery;
import ru.mikhailova.deliveryService.repository.DeliveryRepository;

@Service
@Slf4j
public class SendCarrierDeliveryDetailsServiceImpl implements SendCarrierDeliveryDetailsService {
    private final DeliveryRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String deliveryInformationTopic;

    public SendCarrierDeliveryDetailsServiceImpl(DeliveryRepository repository,
                                                 KafkaTemplate<String, Object> kafkaTemplate,
                                                 @Value("${kafka.topic.delivery-information}") String deliveryInformationTopic) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.deliveryInformationTopic = deliveryInformationTopic;
    }

    @Transactional(readOnly = true)
    @Override
    public void sendDeliveryInformation(Long id) {
        Delivery delivery = repository.findById(id).orElseThrow();
        log.info("Delivery information with id: {} is sent", delivery.getId());
        kafkaTemplate.send(deliveryInformationTopic, delivery);
    }
}

