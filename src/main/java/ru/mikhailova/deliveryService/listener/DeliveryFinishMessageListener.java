package ru.mikhailova.deliveryService.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.mikhailova.deliveryService.listener.dto.DeliveryFinishDto;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeliveryFinishMessageListener {
    private final RuntimeService service;

    @KafkaListener(topics = "${kafka.topic.delivery-finish-message}", groupId = "${spring.kafka.consumer.group-id}")
    public void deliveryFinishMessageListener(JsonNode dto) throws JsonProcessingException {
        DeliveryFinishDto deliveryFinishDto = new ObjectMapper().treeToValue(dto, DeliveryFinishDto.class);
        log.info("New delivery finish message from {}", deliveryFinishDto);

        service.createMessageCorrelation("delivery_finish_message")
                .processInstanceVariableEquals("id", deliveryFinishDto.getId())
                .correlateWithResult();
    }
}
