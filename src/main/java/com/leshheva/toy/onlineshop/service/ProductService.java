package com.leshheva.toy.onlineshop.service;

import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return  productRepository.findAllByCategory_IdOrderById(category_id);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsWithFiltering(Specification<Product> productSpecification) {

        return productRepository.findAll(productSpecification);
    }

    public Product getProduct(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);

    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
