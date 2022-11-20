package ru.mikhailova.deliveryService.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.mikhailova.deliveryService.service.cart.CartService;

@Component
@RequiredArgsConstructor
@Slf4j
public class CartDelegate implements JavaDelegate {
    private final CartService cartService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("id");
        log.info("Cart delegate with id: {} executed", id);
        cartService.getCartResponseDtoList(id);
    }
}
