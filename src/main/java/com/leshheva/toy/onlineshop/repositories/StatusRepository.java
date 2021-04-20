package com.leshheva.toy.onlineshop.repositories;

import com.leshheva.toy.onlineshop.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<OrderStatus,Long> {
    OrderStatus findByTitle(String status);

}
