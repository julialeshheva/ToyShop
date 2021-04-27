package com.leshheva.toy.onlineshop.repositories;

import com.leshheva.toy.onlineshop.entities.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product>{
    @Query("SELECT p FROM Product p WHERE p.category =: categoryId ORDER BY p.id")
    List<Product> findAllByCategory_IdOrderById(@Param("categoryId")Long categoryId);


}
