package com.leshheva.toy.onlineshop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="orders_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "item_price")
    private Double itemPrice;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem( Long quantity, Double itemPrice, Double totalPrice, Product product) {

        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.totalPrice = totalPrice;
        this.product = product;
    }
}
