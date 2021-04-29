package com.leshheva.toy.onlineshop.service;

import com.leshheva.toy.onlineshop.entities.OrderItem;
import com.leshheva.toy.onlineshop.entities.Product;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartService {

    private ProductService productService;
    List<OrderItem> orderItems;


    @Autowired
    public void setProductsService(ProductService productsService) {
        this.productService = productsService;
    }

    public ShoppingCartService() {
        orderItems = new ArrayList<>();
    }

    public void add(Long product_id) {
       Product product=  productService.getProduct(product_id);
       OrderItem orderItem = orderItems.stream().filter(item ->item.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
       if (orderItem == null){
           orderItem =new OrderItem();
           orderItem.setProduct(product);
           orderItem.setQuantity(1L);
           orderItem.setItemPrice(product.getPrice());
           orderItem.setTotalPrice(product.getPrice());
           orderItems.add(orderItem);
       }
       else {
           orderItem.setQuantity(orderItem.getQuantity()+1);
           orderItem.setTotalPrice(product.getPrice()*orderItem.getQuantity());

       }
        for (OrderItem item: orderItems
        ) {
            System.out.println(item.getProduct().getTitle()+" Total price "+item.getTotalPrice()+" Kolvo " + item.getQuantity());
        }

    }

    public void removeProduct(Long id){
        Product product =productService.getProduct(id);
        OrderItem orderItem = orderItems.stream().filter(item->item.getProduct().equals(product)).findFirst().orElse(null);
        if(orderItem.getQuantity()>1){
            orderItem.setQuantity(orderItem.getQuantity()-1);
            orderItem.setTotalPrice(orderItem.getTotalPrice()-product.getPrice());
        }else {
            orderItems.remove(orderItem);
        }
    }

    public Double totalOrderPrice(){
        Double totalPrice = 0.0;
        for (OrderItem item: orderItems
        ) {
           totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }


}
