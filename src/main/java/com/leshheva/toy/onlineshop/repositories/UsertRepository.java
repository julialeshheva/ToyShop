package com.leshheva.toy.onlineshop.repositories;

import com.leshheva.toy.onlineshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsertRepository extends JpaRepository<User,Long> {
}
