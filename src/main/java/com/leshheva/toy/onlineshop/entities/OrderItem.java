package com.leshheva.toy.onlineshop.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="orders_item")
public class OrderItem {

    @Id
    @GeneratedValue
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

}
