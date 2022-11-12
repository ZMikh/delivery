package ru.mikhailova.service.serviceTask.shoppingcart;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.mikhailova.domain.Delivery;

/**
 * Сервис корзины покаупателя
 */
public interface ShoppingcartService {
    /**
     * Получить содержимое корзины покупателя по идентификатору доставки
     *
     * @param id идентификатор доставки
     * @return сущность Доставка
     * @throws JsonProcessingException
     */
    Delivery getDelivery(Long id) throws JsonProcessingException;
}
