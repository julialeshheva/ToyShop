package com.leshheva.toy.onlineshop.controllers;

import com.leshheva.toy.onlineshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductsService productsService;

    @Autowired
    public void setProductService(ProductsService productService) {
        this.productsService = productService;
    }

    @GetMapping
    public String showProductsList(Model model){

        model.addAttribute("products", productsService.getAllProducts()); // мы у сервиса запросили список продуктов
        return "products";

    }
}
