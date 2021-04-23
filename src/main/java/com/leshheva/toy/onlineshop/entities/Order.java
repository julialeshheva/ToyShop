package com.leshheva.toy.onlineshop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus orderStatus;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @Column(name = "order_price")
    private Double orderPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
