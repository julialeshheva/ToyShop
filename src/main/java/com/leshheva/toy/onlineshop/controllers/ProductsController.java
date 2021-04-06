package com.leshheva.toy.onlineshop.controllers;


import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.repositories.specifications.ProductSpecification;
import com.leshheva.toy.onlineshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/products")
public class ProductsController {

    private static final int INITIAL_PAGE = 0;
    private static final int PAGE_SIZE = 5;

    private ProductsService productsService;

    @Autowired
    public void setProductService(ProductsService productService) {
        this.productsService = productService;
    }

    @GetMapping
    public String showProductsList(Model model,
                                   @RequestParam(value = "page") Optional<Integer> page,
                                   @RequestParam(value = "word", required = false) String word,
                                   @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                   @RequestParam(value = "maxPrice", required = false) Integer maxPrice){
        final int currentPage = (page.orElse(0)<1) ? INITIAL_PAGE :page.get() - 1;

        Specification<Product> specification = Specification.where(null);
        if (word !=null){
            specification = specification.and(ProductSpecification.titleContains(word));
        }
        if (minPrice != null){
            specification = specification.and(ProductSpecification.priceGreaterThanOrEq(minPrice));
        }
        if (maxPrice != null){
            specification = specification.and(ProductSpecification.priceLesserThanOrEq(maxPrice));
        }
        model.addAttribute("products", productsService.getAllProductsWithPagingAndFiltering(specification, currentPage, PAGE_SIZE).getContent());
        model.addAttribute("word", word);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "products";

    }
    @GetMapping("/add")
    public String showAddProductForm(Model model){
        Product product =new Product();
        model.addAttribute("product", product);
        return "products-edit";

    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(Model model, @PathVariable(value = "id")Long id){
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/edit")
    public String addOrUpdateProduct(@ModelAttribute(value = "product") Product product){
        productsService.saveOrUpdate(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(Model model,  @PathVariable(value = "id") Long id){
        productsService.deleteById(id);
        return "redirect:/products";

    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model,  @PathVariable(value = "id") Long id){
        Product product = productsService.getById(id);
       model.addAttribute("product",product);
        return "product-page";

    }

}
