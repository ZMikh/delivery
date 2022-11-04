package ru.mikhailova.service;

import lombok.Builder;
import lombok.Data;
import ru.mikhailova.domain.DeliveryState;

import java.time.LocalDateTime;
@Data
@Builder
public class DeliveryUpdateInfo {
    private DeliveryState state;
    private LocalDateTime deliveryTime;
    private String description;
}
