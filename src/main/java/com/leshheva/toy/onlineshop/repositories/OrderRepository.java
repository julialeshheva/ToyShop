package com.leshheva.toy.onlineshop.repositories;

import com.leshheva.toy.onlineshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
