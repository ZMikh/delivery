package ru.mikhailova.service.userTask;

import lombok.Data;
import ru.mikhailova.domain.ConfirmState;

import java.time.LocalDateTime;

/**
 * Параметры подтверждения доставки
 */
@Data
public class DeliveryConfirmInfo {
    /**
     * Параметр подтверждения заказа
     */
    private ConfirmState confirmationState;
    /**
     * Адрес доставки
     */
    private String address;
    /**
     * Получение заказа самовывозом
     */
    private Boolean isPickUp;
    /**
     * Время доставки
     */
    private LocalDateTime deliveryTime;
    /**
     * Описание доставки
     */
    private String description;
}
