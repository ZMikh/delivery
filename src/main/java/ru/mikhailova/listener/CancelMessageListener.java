package ru.mikhailova.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CancelMessageListener {
    private final RuntimeService service;

    @KafkaListener(topics = "cancelMessage")
    public void messageListener(JsonNode dto) throws JsonProcessingException {
        DeliveryMessageDto deliveryMessageDto = new ObjectMapper().treeToValue(dto, DeliveryMessageDto.class);
        log.info("new cancel message from {}", deliveryMessageDto);

        service.createMessageCorrelation("cancel_message")
                .processInstanceVariableEquals("id", deliveryMessageDto.getId())
                .correlateWithResult();
    }
}
