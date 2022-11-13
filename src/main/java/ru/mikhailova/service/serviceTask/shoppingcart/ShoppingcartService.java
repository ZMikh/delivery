package ru.mikhailova.service.serviceTask.shoppingcart;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.mikhailova.dto.ShoppingcartResponseDtoList;

/**
 * Сервис корзины покаупателя
 */
public interface ShoppingcartService {
    /**
     * Получить содержимое корзины покупателя по идентификатору доставки
     *
     * @param id идентификатор доставки
     * @return
     * @throws JsonProcessingException
     */
    ShoppingcartResponseDtoList getShoppingcartResponseDtoList(Long id) throws JsonProcessingException;
}
