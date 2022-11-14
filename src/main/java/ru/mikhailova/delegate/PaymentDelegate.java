package ru.mikhailova.delegate;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.mikhailova.domain.Delivery;
import ru.mikhailova.repository.DeliveryRepository;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class PaymentDelegate implements JavaDelegate {
    private final DeliveryRepository repository;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        Long id = (Long) delegateExecution.getVariable("id");
        Delivery delivery = repository.findById(id).orElseThrow();
        String description = delivery.getDescription();
        if (nonNull(description) && description.contains("error")) {
            throw new BpmnError("PAYMENT_ERROR");
        }
    }
}
