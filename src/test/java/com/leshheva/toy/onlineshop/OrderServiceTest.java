package com.leshheva.toy.onlineshop;

import com.leshheva.toy.onlineshop.entities.*;
import com.leshheva.toy.onlineshop.repositories.OrderRepository;
import com.leshheva.toy.onlineshop.service.OrderService;
import com.leshheva.toy.onlineshop.service.ShoppingCartService;
import com.leshheva.toy.onlineshop.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Mock
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;
    @Mock
    private UserService userService;

    List<Product> productList;
    User user;
    List<OrderItem> orderItemList;

    @Before
    public void init(){
        productList = new ArrayList<>();
        productList.add(new Product(1L, "Product1", 100.0));
        productList.add(new Product(2L, "Product2", 200.0));
        user = new User("user", "123", "Bob","Ivanov", "bob@mail.ru");
        orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem(2L, productList.get(0).getPrice(), productList.get(0).getPrice()*2, productList.get(0)));
        orderItemList.add(new OrderItem(1L, productList.get(1).getPrice(), productList.get(1).getPrice()*1, productList.get(1)));

        when(shoppingCartService.getOrderItems()).thenReturn(orderItemList);
        when(shoppingCartService.totalOrderPrice()).thenReturn(orderItemList.stream().mapToDouble(orderItem -> orderItem.getTotalPrice()).sum());
        when(userService.findUserById(1L)).thenReturn(user);
    }

    @Test
    public void shoppingCartadd() {

        assertThat(shoppingCartService.getOrderItems().size()).isEqualTo(orderItemList.size());
        assertThat(shoppingCartService.totalOrderPrice()).isEqualTo(400);
    }

    @Test
    public void createOrder() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        Order order;
        Class orderservice = OrderService.class;
        Method method =  orderservice.getDeclaredMethod("createOrder", User.class, List.class, Double.class);
        method.setAccessible(true);
        order = (Order) method.invoke(orderService, userService.findUserById(1L), shoppingCartService.getOrderItems(), shoppingCartService.totalOrderPrice());

        Assert.assertNotNull(order.getOrderItems());
        Assert.assertNotNull(order.getOrderStatus());
        Assert.assertNotNull(order.getOrderPrice());
    }

}
