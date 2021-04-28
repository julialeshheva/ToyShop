package com.leshheva.toy.onlineshop.repositories.specifications;

import com.leshheva.toy.onlineshop.entities.Order;
import com.leshheva.toy.onlineshop.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class OrderSpecification {


    public static Specification<Order> creationDateGreaterThanOrEq(LocalDateTime date) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("createAt"), date);
        };

    }
    public static Specification<Order> priceGreaterThanOrEq(double value) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("orderPrice"), value);
        };
    }

}
