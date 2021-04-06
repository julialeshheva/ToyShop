package com.leshheva.toy.onlineshop.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name="id")
    private  Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;



    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }


}
