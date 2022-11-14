package ru.mikhailova.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("Список моделей ответа по запросу корзины заказа")
public class ShoppingcartResponseDtoList {
    private List<ShoppingcartResponseDto> shoppingcarts;
}
