package com.leshheva.toy.onlineshop.service;


import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Page<Product> getAllProductsWithPagingAndFiltering(Specification<Product> specification, int pageNumber, int pageSize){
        return productRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));
    }

    public Product getById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public  void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }
}
