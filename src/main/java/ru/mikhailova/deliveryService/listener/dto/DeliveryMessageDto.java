package ru.mikhailova.deliveryService.listener.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Параметры модели отправки сообщения о технической отмене заказа")
public class DeliveryMessageDto {
    @ApiModelProperty("Идентификатор доставки")
    private Long id;
}