package com.leshheva.toy.onlineshop.controllers;

import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.service.CategoryService;
import com.leshheva.toy.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/toyshop")
public class MainController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showMainPage(Model model){
        return "index";
    }

    @GetMapping("/category")
    public String showCategories(Model model){

        model.addAttribute("categories", categoryService.getAllCategories());
        return "category";
    }





}
