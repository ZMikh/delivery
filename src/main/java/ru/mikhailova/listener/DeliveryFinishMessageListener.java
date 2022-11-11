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
public class DeliveryFinishMessageListener {
    private final RuntimeService service;
    @KafkaListener(topics = "deliveryFinishMessage", groupId = "delivery")
    public void deliveryFinishMessageListener(JsonNode dto) throws JsonProcessingException {
        DeliveryFinishDto deliveryFinishDto = new ObjectMapper().treeToValue(dto, DeliveryFinishDto.class);
        log.info("new delivery finish message from {}", deliveryFinishDto);

        service.createMessageCorrelation("delivery_finish_message")
                .processInstanceVariableEquals("id", deliveryFinishDto.getId())
                .correlateWithResult();
    }
}
