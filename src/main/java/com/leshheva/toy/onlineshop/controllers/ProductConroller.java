package com.leshheva.toy.onlineshop.controllers;

import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.repositories.specifications.ProductSpecification;
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
@RequestMapping("/toys")
public class ProductConroller {

    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public  String showProductDetails(Model model, @PathVariable(value="id")Long id){
        model.addAttribute("product", productService.findProductById(id));
        return "product-page";
    }
    @GetMapping("/category/{id}")
    public  String showProductsInCategory(Model model, @PathVariable(value="id")Long id
/*                                          @RequestParam(value = "word", required = false) String word,
                                          @RequestParam(value = "min", required = false) Double min,
                                          @RequestParam(value = "max", required = false) Double max*/){

/*        Specification<Product> spec = Specification.where(null);
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
        }*/
        model.addAttribute("products", productService.getListOfProductsCurrCategory(id));
/*        model.addAttribute("filters", filters.toString());

        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("word", word);*/
        return "products";
    }


}
