package ru.mikhailova.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.mikhailova.service.serviceTask.shoppingcart.ShoppingcartService;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShoppingcartDelegate implements JavaDelegate {
    private final ShoppingcartService shoppingcartService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("id");
        log.info("delegate with id: {} executed", id);
//        shoppingcartService.getDelivery(id);
    }
}
