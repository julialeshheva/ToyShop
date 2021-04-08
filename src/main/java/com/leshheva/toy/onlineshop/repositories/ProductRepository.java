package com.leshheva.toy.onlineshop.repositories;

import com.leshheva.toy.onlineshop.entities.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

    List<Product> findAllByCategory_Id(Long categoryId);
}
