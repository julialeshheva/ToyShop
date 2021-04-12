package com.leshheva.toy.onlineshop.controllers;

import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.repositories.specifications.ProductSpecification;
import com.leshheva.toy.onlineshop.service.CategoryService;
import com.leshheva.toy.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/toys")
public class ProductConroller {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public  String showProductDetails(Model model, @PathVariable(value="id")Long id){
        model.addAttribute("product", productService.findProductById(id));
        return "product-page";
    }
    @GetMapping("/category/{id}")
    public  String showProductsInCategory(Model model, @PathVariable(value="id")Long id, HttpServletRequest httpServletRequest,
                                          @RequestParam(value = "word", required = false) String word,
                                          @RequestParam(value = "min", required = false) Double min,
                                          @RequestParam(value = "max", required = false) Double max){

        Specification<Product> spec = Specification.where(null);
        StringBuilder filters = new StringBuilder();
        if (word != null) {
            spec = spec.and(ProductSpecification.titleContains(word));
            filters.append("&word=" + word);

        }
        if (min != null) {
            spec = spec.and(ProductSpecification.priceGreaterThanOrEq(min));
            filters.append("&min=" + min);

        }
        if (max != null) {
            spec = spec.and(ProductSpecification.priceLesserThanOrEq(max));
            filters.append("&max=" + max);
        }
        model.addAttribute("products", productService.getListOfProductsCurrCategory(id));
        model.addAttribute("filters", filters.toString());

        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("word", word);
        String referrer = httpServletRequest.getHeader("referer");
        System.out.println(referrer);
        return "products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(Model model,  @PathVariable(value="id")Long id){
        model.addAttribute("product",productService.findProductById(id));
        model.addAttribute("categories",categoryService.getAllCategories());
        return "edit-product";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(Model model,  @PathVariable(value="id")Long id){
        productService.deleteProductById(id);
        return "edit-product";
    }
    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "edit-product";
    }


    @PostMapping("/edit")
    public String showAddCatForm(@ModelAttribute(value = "product")  Product product){
       productService.saveProduct(product);
        return "redirect:index";
    }



}
