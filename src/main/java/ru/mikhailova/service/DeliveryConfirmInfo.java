package ru.mikhailova.service;

import lombok.Data;
import ru.mikhailova.domain.ConfirmState;

import java.time.LocalDateTime;

@Data
public class DeliveryConfirmInfo {
    private ConfirmState state;
    private String address;
    private Boolean isPickUp;
    private LocalDateTime deliveryTime;
}
