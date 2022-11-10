package ru.mikhailova.service.receiveTask;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiveDeliveryFinishInformationServiceImpl implements ReceiveDeliveryFinishInformationService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public void receiveMessageTask() {
        kafkaTemplate.receive("deliveryFinishMessage", 0, 1L);
    }
}
