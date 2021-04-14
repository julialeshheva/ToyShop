package com.leshheva.toy.onlineshop.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @NotNull(message = "категория не выбрана")
    private Category category;
}
