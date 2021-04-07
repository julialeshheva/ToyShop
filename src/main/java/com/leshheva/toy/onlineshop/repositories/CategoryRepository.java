package com.leshheva.toy.onlineshop.repositories;

import com.leshheva.toy.onlineshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
