package ru.mikhailova.service;

import lombok.Data;
import ru.mikhailova.domain.DeliveryState;

import java.time.LocalDateTime;
@Data
public class UpdateDeliveryInfo {
    private DeliveryState state;
    private LocalDateTime deliveryTime;
    private String description;
}
