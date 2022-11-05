package ru.mikhailova.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikhailova.domain.DeliveryState;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryUpdateInfo {
    private DeliveryState state;
    private LocalDateTime deliveryTime;
    private String description;
}
