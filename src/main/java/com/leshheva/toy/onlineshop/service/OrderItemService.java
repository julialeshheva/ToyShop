package com.leshheva.toy.onlineshop.service;

import com.leshheva.toy.onlineshop.entities.Order;
import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    OrderItemRepository orderItemRepository;
    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

/*    public List<Product> getListOfProductsByOrderId(List<Order> orders){
      // return orderItemRepository.findAllByOrderInOrderById(orders);
    }*/
}
