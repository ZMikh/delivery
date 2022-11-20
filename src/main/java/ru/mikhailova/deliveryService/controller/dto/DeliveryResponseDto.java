package ru.mikhailova.deliveryService.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("Параметры для ответа по доставке")
public class DeliveryResponseDto {
    @ApiModelProperty("Идентификатор доставки")
    private Long id;
    @ApiModelProperty("Статус доставки")
    private String state;
    @ApiModelProperty("Время доставки")
    private LocalDateTime deliveryTime;
    @ApiModelProperty("Описание доставки")
    private String description;
    @ApiModelProperty("Адрес доставки")
    private String address;
    @ApiModelProperty("Получение доставки самовывозом")
    private Boolean isPickUp;
}
