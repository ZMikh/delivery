package ru.mikhailova.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("Параметры запроса для подтверждения заказа")
public class DeliveryRequestConfirmDto {
    @ApiModelProperty("Статус подтверждение доствки")
    private String confirmationState;
    @ApiModelProperty("Адрес доставки")
    private String address;
    @ApiModelProperty("Получение заказа самовывозом")
    private Boolean isPickUp;
    @ApiModelProperty("Время доставки")
    private LocalDateTime deliveryTime;
    @ApiModelProperty("Описание доставки")
    private String description;
}
