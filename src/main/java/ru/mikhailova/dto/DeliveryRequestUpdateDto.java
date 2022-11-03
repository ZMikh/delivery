package ru.mikhailova.dto;

import lombok.Data;
import ru.mikhailova.domain.DeliveryState;

import java.time.LocalDateTime;

@Data
public class DeliveryRequestUpdateDto {
    private DeliveryState state;
    private LocalDateTime deliveryTime;
    private String description;
}
