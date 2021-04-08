package com.leshheva.toy.onlineshop.service;

import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getListOfProductsCurrCategory(Long category_id) {
        return  productRepository.findAllByCategory_Id(category_id);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

/*    public List<Product> getProductsWithFiltering(Specification<Product> productSpecification) {

        return productRepository.findAll(productSpecification);
    }*/
}
