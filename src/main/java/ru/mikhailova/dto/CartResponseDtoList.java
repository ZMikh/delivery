package ru.mikhailova.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("Список моделей ответа по запросу корзины заказа")
public class CartResponseDtoList {
    @ApiModelProperty("Состав корзины соответствующего заказа")
    private List<CartResponseDto> carts;
}
