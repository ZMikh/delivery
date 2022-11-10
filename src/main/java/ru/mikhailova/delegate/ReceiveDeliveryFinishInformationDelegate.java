package ru.mikhailova.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.mikhailova.service.receiveTask.ReceiveDeliveryFinishInformationService;

@Component
@RequiredArgsConstructor
public class ReceiveDeliveryFinishInformationDelegate implements JavaDelegate {
    private final ReceiveDeliveryFinishInformationService service;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        service.receiveMessageTask();
    }
}
