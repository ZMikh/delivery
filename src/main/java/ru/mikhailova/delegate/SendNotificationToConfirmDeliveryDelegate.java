package ru.mikhailova.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.mikhailova.service.sendNotification.SendNotificationToConfirmDeliveryService;

@Component
@RequiredArgsConstructor
public class SendNotificationToConfirmDeliveryDelegate implements JavaDelegate {
    private final SendNotificationToConfirmDeliveryService sendNotificationToConfirmDeliveryService;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        Long id = (Long) delegateExecution.getVariable("id");
        sendNotificationToConfirmDeliveryService.sendNotification(id);
    }
}
