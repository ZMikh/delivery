package ru.mikhailova.deliveryService.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.mikhailova.deliveryService.service.sendDeliveryInfornation.SendCarrierDeliveryDetailsService;

@Component
@RequiredArgsConstructor
public class SendCarrierDeliveryDetailsDelegate implements JavaDelegate {
    private final SendCarrierDeliveryDetailsService sendCarrierDeliveryDetailsService;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        Long id = (Long) delegateExecution.getVariable("id");
        sendCarrierDeliveryDetailsService.sendDeliveryInformation(id);
    }
}
