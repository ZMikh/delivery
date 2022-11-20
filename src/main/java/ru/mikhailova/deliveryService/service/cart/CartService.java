package ru.mikhailova.deliveryService.service.cart;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Сервис корзины покаупателя
 */
public interface CartService {
    /**
     * Получить содержимое корзины покупателя по идентификатору доставки
     *
     * @param id идентификатор доставки
     * @throws JsonProcessingException
     */
    void getCartResponseDtoList(Long id) throws JsonProcessingException;
}
