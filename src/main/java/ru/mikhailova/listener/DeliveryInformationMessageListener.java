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
@RequiredArgsConstructor
@Slf4j
public class DeliveryInformationMessageListener {
    private final RuntimeService service;

    @KafkaListener(topics = "deliveryInformation", groupId = "delivery")
    public void messageListener(JsonNode dto) throws JsonProcessingException {
        DeliveryMessageDto deliveryMessageDto = new ObjectMapper().treeToValue(dto, DeliveryMessageDto.class);
        log.info("delivery information message from {}", deliveryMessageDto);

        service.createMessageCorrelation("delivery_information")
                .processInstanceVariableEquals("id", deliveryMessageDto.getId())
                .correlateWithResult();
    }
}
