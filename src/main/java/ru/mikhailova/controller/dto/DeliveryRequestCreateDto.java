package ru.mikhailova.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Параметры для запроса по созданию доставки")
public class DeliveryRequestCreateDto {
    @ApiModelProperty("Описание доставки")
    private String description;
}
