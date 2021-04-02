package com.leshheva.toy.onlineshop.controllers;

import com.leshheva.toy.onlineshop.configuration.Cat;
import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        Product product = new Product();
        model.addAttribute("product",product);
        return "products";

    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product){
        productsService.add(product);
        return "redirect:/products";

    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model,  @PathVariable(value = "id") Long id){
        Product product = productsService.getById(id);
        model.addAttribute("product",product);
        return "product-page";

    }

}
