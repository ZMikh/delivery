package ru.mikhailova.service.userTask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryUpdateInfo {
    private LocalDateTime deliveryTime;
    private String description;
}
