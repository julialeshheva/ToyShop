package com.leshheva.toy.onlineshop.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

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

    @NotNull(message = "Введите название продукта")
    @Size(min = 2, max =100, message = "Название должно быть от 2 до 100 символов")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Введите описание")
    @Size(min = 10, max =300, message = "Описание должно быть меньше от 10 до 300 символов")
    @Column(name = "full_description")
    private String description;

    @DecimalMin(value = "10.01", message = "минимальное значение 10")
    @Digits(integer = 10, fraction = 2)
    @NotNull(message = "Цена не должна быть пустой")
    @Column(name = "price")
    private Double price;

    @Column(name = "path_img")
    private String path_img;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "категория не выбрана")
    private Category category;

    public Product(String path_img) {
        this.path_img = path_img;
    }

    public Product(Long id, @NotNull(message = "Введите название продукта") @Size(min = 2, max = 100, message = "Название должно быть от 2 до 100 символов") String title, @DecimalMin(value = "10.01", message = "минимальное значение 10") @Digits(integer = 10, fraction = 2) @NotNull(message = "Цена не должна быть пустой") Double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
