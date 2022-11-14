package ru.mikhailova.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.mikhailova.service.sendTask.sendDeliveryInfornation.SendDeliveryInformationService;

@Component
@RequiredArgsConstructor
public class SendDeliveryInformationDelegate implements JavaDelegate {
    private final SendDeliveryInformationService sendDeliveryInformationService;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        Long id = (Long) delegateExecution.getVariable("id");
        sendDeliveryInformationService.sendDeliveryInformation(id);
    }
}
