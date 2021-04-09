package com.leshheva.toy.onlineshop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order{

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(name = "status")
    private OrderStatus orderStatus;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

}
