package com.leshheva.toy.onlineshop.repositories;

import com.leshheva.toy.onlineshop.entities.Order;
import com.leshheva.toy.onlineshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUserOrderById(User user);
    List<Order> findAllByOrderByIdAsc();
}
