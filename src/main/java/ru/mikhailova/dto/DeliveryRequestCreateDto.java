package ru.mikhailova.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryRequestCreateDto {
    private LocalDateTime deliveryTime;
    private String description;
}
