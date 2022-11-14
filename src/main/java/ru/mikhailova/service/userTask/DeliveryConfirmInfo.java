package ru.mikhailova.service.userTask;

import lombok.Data;
import ru.mikhailova.domain.ConfirmState;

import java.time.LocalDateTime;

@Data
public class DeliveryConfirmInfo {
    private ConfirmState state;
    private String address;
    private Boolean isPickUp;
    private LocalDateTime deliveryTime;
    private String description;
}
