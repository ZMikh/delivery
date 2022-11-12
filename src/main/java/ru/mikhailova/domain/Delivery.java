package ru.mikhailova.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Доставка
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    /**
     * Идентификатор доставки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_id")
    @SequenceGenerator(name = "delivery_id", sequenceName = "delivery_sequence", allocationSize = 1)
    private Long id;
    /**
     * Статус доставки
     */
    @Enumerated(value = EnumType.STRING)
    private DeliveryState state;
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
    /**
     * Получение заказа самовывозом
     */
    private Boolean isPickUp;
    /**
     * Список товаров из корзины покупателя
     */
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "delivery", fetch = FetchType.LAZY)
    private List<Shoppingcart> shoppingcartList;
}
