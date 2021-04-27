package com.leshheva.toy.onlineshop.repositories;

import com.leshheva.toy.onlineshop.entities.Order;
import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>, JpaSpecificationExecutor<Order> {

    @Query("SELECT o FROM Order o WHERE o.user =:user")
    List<Order> findAllByUserOrderById(@Param("user") User user);

    @Query("SELECT o FROM Order o ORDER BY o.id ASC")
    List<Order> findAllByOrderByIdAsc();

}
