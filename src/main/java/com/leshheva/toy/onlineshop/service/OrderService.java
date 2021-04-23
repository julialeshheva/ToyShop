package com.leshheva.toy.onlineshop.service;

import com.leshheva.toy.onlineshop.entities.Order;
import com.leshheva.toy.onlineshop.entities.OrderItem;
import com.leshheva.toy.onlineshop.entities.OrderStatus;
import com.leshheva.toy.onlineshop.entities.User;
import com.leshheva.toy.onlineshop.repositories.OrderRepository;
import com.leshheva.toy.onlineshop.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    OrderRepository orderRepository;
    StatusRepository statusRepository;


    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Autowired
    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public void createOrder(User user, List<OrderItem> orderItemList, Double orderPrice){
        Order order = new Order();

        order.setId(0L);
        order.setUser(user);
        order.setOrderItems(orderItemList);
        order.setOrderStatus(statusRepository.findByTitle(OrderStatus.Statuses.CREATED.toString()));
        order.setOrderPrice(orderPrice);
        order.setDeliveryDate(LocalDateTime.now().plusDays(3));

        for (OrderItem orderItem: orderItemList
             ) {
            orderItem.setOrder(order);
        }
        orderRepository.save(order);
    }

    public List<Order> findOrderByUserId(User user) {
        return orderRepository.findAllByUserOrderById(user);
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAllByOrderByIdAsc();
    }

    public void saveOrders(List<Order> orders) {
        orderRepository.saveAll(orders);
    }
}
