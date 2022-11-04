package ru.mikhailova.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.mikhailova.domain.DeliveryState;

import java.time.LocalDateTime;

@Data
@ApiModel("Параметры для ответа по доставке")
public class DeliveryResponseDto {
    @ApiModelProperty("Идентификатор доставки")
    private Long id;
    @ApiModelProperty("Статус доставки")
    private DeliveryState state;
    @ApiModelProperty("Время доставки")
    private LocalDateTime deliveryTime;
    @ApiModelProperty("Описание доставки")
    private String description;
}
