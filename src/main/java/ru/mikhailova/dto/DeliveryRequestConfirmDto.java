package ru.mikhailova.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryRequestConfirmDto {
    private String state;
    private String address;
    private Boolean isPickUp;
    private LocalDateTime deliveryTime;
}
