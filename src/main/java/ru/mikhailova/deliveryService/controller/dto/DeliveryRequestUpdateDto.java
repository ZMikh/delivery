package ru.mikhailova.deliveryService.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ApiModel("Параметры для запроса по обновлению параметров доставки")
public class DeliveryRequestUpdateDto {
    @ApiModelProperty("Время доставки")
    private LocalDateTime deliveryTime;
    @ApiModelProperty("Описание доставки")
    private String description;
}
