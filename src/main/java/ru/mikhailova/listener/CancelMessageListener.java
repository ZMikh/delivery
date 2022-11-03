package ru.mikhailova.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CancelMessageListener {

    @KafkaListener(topics = "cancelMessage")
    public void messageListener(DeliveryMessageDto deliveryMessageDto) {
        log.info("new cancel message from {}", deliveryMessageDto);
    }
}
