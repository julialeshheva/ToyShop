package com.leshheva.toy.onlineshop.repositories;

import com.leshheva.toy.onlineshop.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<OrderStatus,Long> {
    @Query("SELECT s FROM OrderStatus s WHERE s.title = ?1")
    OrderStatus findByTitle(@Param("status")String status);

}
