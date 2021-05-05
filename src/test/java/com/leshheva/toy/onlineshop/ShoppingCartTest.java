package com.leshheva.toy.onlineshop;

import com.leshheva.toy.onlineshop.entities.OrderItem;
import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.service.ShoppingCartService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("/application.properties")
@TestPropertySource("/application.properties")
public class ShoppingCartTest {

    @Autowired
    ShoppingCartService shoppingCart;

    private List<Long> productsId;

    @Before
    public void fillProductsId(){
        productsId = new ArrayList<>();
        productsId.add(1L);
        productsId.add(1L);
        productsId.add(2L);
        productsId.add(3L);
    }
    @Test
    public void checkCartAddingTest(){

        for (Long id:productsId
             ) {
            shoppingCart.add(id);
        }
        Assert.assertTrue(shoppingCart.getOrderItems().size()>0);
    }
    @Test
    public void getTotalPriceTest(){

        for (Long id:productsId
        ) {
            shoppingCart.add(id);
        }
        Assert.assertEquals(1366,shoppingCart.totalOrderPrice(),0.0001);
    }

    @Test
    public void deleteProductTest(){
        for (Long id:productsId
        ) {
            shoppingCart.add(id);
        }
        int  size = shoppingCart.getOrderItems().size();
        shoppingCart.removeProduct(2L);
        Assert.assertEquals(900,shoppingCart.totalOrderPrice(),0.0001);
        Assert.assertEquals(size-1,shoppingCart.getOrderItems().size());

    }

    @Test
    public void deleteProductQuantityTest(){
        for (Long id:productsId
        ) {
            shoppingCart.add(id);
        }
        Long  curQuantaty = shoppingCart.getOrderItems().get(0).getQuantity();
        shoppingCart.removeProduct(1L);
        Long newQuantaty = shoppingCart.getOrderItems().get(0).getQuantity();
        Assert.assertEquals(1166,shoppingCart.totalOrderPrice(),0.0001);
        Assert.assertEquals(200,shoppingCart.getOrderItems().get(0).getTotalPrice(),0.0001);
        Assert.assertEquals(curQuantaty - 1, newQuantaty.longValue());
    }


}
