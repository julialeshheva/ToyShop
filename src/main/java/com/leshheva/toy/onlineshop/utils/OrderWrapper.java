package com.leshheva.toy.onlineshop.utils;

import com.leshheva.toy.onlineshop.entities.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderWrapper {

    private List<Order> orders;

    public OrderWrapper(List<Order> orders) {
        this.orders = orders;
    }

    public void editOrder(Order order) {
        this.orders.add(order);
    }

}
