package ru.mikhailova.service.serviceTask.shoppingcart;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Сервис корзины покаупателя
 */
public interface ShoppingcartService {
    /**
     * Получить содержимое корзины покупателя по идентификатору доставки
     *
     * @param id идентификатор доставки
     * @throws JsonProcessingException
     */
    void getShoppingcartResponseDtoList(Long id) throws JsonProcessingException;
}
