package com.leshheva.toy.onlineshop.service;

import com.leshheva.toy.onlineshop.entities.OrderStatus;
import com.leshheva.toy.onlineshop.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {
    StatusRepository orderStatusRepository;

    @Autowired
    public void setOrderStatusRepository(StatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }


    public List<OrderStatus> findAllStatuses() {
        return orderStatusRepository.findAll();
    }
}
