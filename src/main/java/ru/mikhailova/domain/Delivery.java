package ru.mikhailova.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_id")
    @SequenceGenerator(name = "delivery_id", sequenceName = "delivery_sequence", allocationSize = 1)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private DeliveryState state;

    private LocalDateTime deliveryTime;
    private String description;
}
