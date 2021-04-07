package com.leshheva.toy.onlineshop.repositories;

import com.leshheva.toy.onlineshop.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
