package ru.mikhailova.listener;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Параметры модели завершения доставки")
public class DeliveryFinishDto {
    @ApiModelProperty("Идентификатор доставки")
    private Long id;
}
