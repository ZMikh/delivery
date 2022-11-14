package ru.mikhailova.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Корзина покупателя
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shoppingcart {
    /**
     * Идентификатор товара
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shoppingcart_id")
    @SequenceGenerator(name = "shoppingcart_id", sequenceName = "shoppingcart_sequence", allocationSize = 1)
    private Long id;
    /**
     * Наименование товара
     */
    private String productName;
    /**
     * Артикул товара
     */
    private String articleNumber;
    /**
     * Количество в шт.
     */
    private Long amount;
    /**
     * Цена за шт.
     */
    private Double pricePerItem;
    /**
     * Стомость за указанное количество
     */
    private Double totalCost;
    /**
     * Описание товара
     */
    private String description;

    public void setTotalCost(Long amount, Double pricePerItem) {
        this.totalCost = pricePerItem * amount;
    }
}
