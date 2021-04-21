package com.leshheva.toy.onlineshop.controllers;


import com.leshheva.toy.onlineshop.entities.Order;
import com.leshheva.toy.onlineshop.entities.OrderItem;
import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.entities.User;
import com.leshheva.toy.onlineshop.service.OrderService;
import com.leshheva.toy.onlineshop.service.ProductService;
import com.leshheva.toy.onlineshop.service.ShoppingCartService;
import com.leshheva.toy.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    ShoppingCartService shoppingCartService;
    ProductService productService;
    OrderService orderService;
    UserService userService;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String createOrder(Model model, Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("order", new Order());
        model.addAttribute("orderItems", shoppingCartService.getOrderItems());
        model.addAttribute("totalPrice", shoppingCartService.totalOrderPrice());
        return "order-purchase";
    }

    @PostMapping("/buy")
    public  String registrateOrder(Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        orderService.createOrder(user,shoppingCartService.getOrderItems(), shoppingCartService.totalOrderPrice());
        return "index";
    }



}
