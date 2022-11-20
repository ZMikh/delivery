package ru.mikhailova.deliveryService.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.mikhailova.deliveryService.mapper.StateMapper;
import ru.mikhailova.deliveryService.service.changeState.DeliveryChangeStateService;

@Component
@RequiredArgsConstructor
public class StateChangeDelegate implements JavaDelegate {
    private final DeliveryChangeStateService service;
    private final StateMapper mapper;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        Long id = (Long) delegateExecution.getVariable("id");
        String activityInstanceId = delegateExecution.getCurrentActivityId();
        service.changeDeliveryState(mapper.getStateByActivityId(activityInstanceId), id);
    }
}
