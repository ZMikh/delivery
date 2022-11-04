package ru.mikhailova.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("Параметры для ответа по созданию доставки")
public class DeliveryRequestCreateDto {
    @ApiModelProperty("Время доставки")
    private LocalDateTime deliveryTime;
    @ApiModelProperty("Описание доставки")
    private String description;
}
