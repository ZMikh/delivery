package ru.mikhailova.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.mikhailova.service.sendNotification.SendNotificationService;

@Component
@RequiredArgsConstructor
public class SendNotificationDelegate implements JavaDelegate {
    private final SendNotificationService sendNotificationService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("id");
        sendNotificationService.sendNotification(id);
    }
}