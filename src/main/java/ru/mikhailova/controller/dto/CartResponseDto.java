package ru.mikhailova.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Параметры ответа по запросу корзины заказа")
public class CartResponseDto {
    @ApiModelProperty("Идентификатор товара")
    private Long id;
    @ApiModelProperty("Наименование товара")
    private String productName;
    @ApiModelProperty("Артикул товара")
    private String articleNumber;
    @ApiModelProperty("Количество товара в шт.")
    private Long amount;
    @ApiModelProperty("Цена за шт.")
    private Double pricePerItem;
    @ApiModelProperty("Стоимость за указанное количество")
    private Double totalCost;
    @ApiModelProperty("Описание товара")
    private String description;
}

