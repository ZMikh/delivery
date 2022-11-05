package ru.mikhailova.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikhailova.domain.DeliveryState;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ApiModel("Параметры для запроса по обновлению доставки")
public class DeliveryRequestUpdateDto {
    @ApiModelProperty("Статус доставки")
    private DeliveryState state;
    @ApiModelProperty("Время доставки")
    private LocalDateTime deliveryTime;
    @ApiModelProperty("Описание доставки")
    private String description;
}
