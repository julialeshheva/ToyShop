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
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "full_description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "path_img")
    private String path_img;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
