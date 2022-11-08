package ru.mikhailova.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("Параметры запроса по подтверждению заказа")
public class DeliveryRequestConfirmDto {
    @ApiModelProperty("Статус доствки")
    private String state;
    @ApiModelProperty("Адрес доставки")
    private String address;
    @ApiModelProperty("Получение заказа самовывозом")
    private Boolean isPickUp;
    @ApiModelProperty("Время доставки")
    private LocalDateTime deliveryTime;
}
