package ru.mikhailova.service.finish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.repository.DeliveryRepository;

@Service
@Slf4j
public class DeliveryFinishInformationServiceImpl implements DeliveryFinishInformationService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final DeliveryRepository repository;
    private final String deliveryFinishMessageTopic;

    public DeliveryFinishInformationServiceImpl(KafkaTemplate<String, Object> kafkaTemplate,
                                                DeliveryRepository repository,
                                                @Value("${kafka.topic.delivery-finish-message}") String deliveryFinishMessageTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.repository = repository;
        this.deliveryFinishMessageTopic = deliveryFinishMessageTopic;
    }

    @Transactional(readOnly = true)
    @Override
    public void sendMessageTask(Long id) {
        Delivery delivery = repository.findById(id).orElseThrow();
        log.info("Delivery finish information with id: {} is sent", delivery.getId());
        kafkaTemplate.send(deliveryFinishMessageTopic, delivery);
    }
}
