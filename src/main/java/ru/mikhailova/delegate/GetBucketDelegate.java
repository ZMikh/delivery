package ru.mikhailova.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetBucketDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("id");
        log.info("delegate with id: {} executed", id);
    }
}
