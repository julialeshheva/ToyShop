package com.leshheva.toy.onlineshop.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name = "title")
    private String title;

}
