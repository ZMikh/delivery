package ru.mikhailova.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CompensationDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
    }
}
