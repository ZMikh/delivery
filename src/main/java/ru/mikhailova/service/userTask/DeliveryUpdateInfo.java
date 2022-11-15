package ru.mikhailova.service.userTask;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Обновляемые параметры доставки
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryUpdateInfo {
    /**
     * Время доставки
     */
    private LocalDateTime deliveryTime;
    /**
     * Описание доставки
     */
    private String description;
    /**
     * Адрес доставки
     */
    private String address;
}
