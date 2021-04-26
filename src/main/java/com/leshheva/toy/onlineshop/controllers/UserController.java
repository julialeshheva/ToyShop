package com.leshheva.toy.onlineshop.controllers;

import com.leshheva.toy.onlineshop.entities.Order;
import com.leshheva.toy.onlineshop.entities.OrderItem;
import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.entities.User;
import com.leshheva.toy.onlineshop.service.OrderItemService;
import com.leshheva.toy.onlineshop.service.OrderService;
import com.leshheva.toy.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    OrderService orderService;
    UserService userService;
    OrderItemService orderItemService;


    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }


    @GetMapping("/orders/{username}")
    public String viewUserOrders(Model model, @PathVariable(value="username")String username, Principal principal){

        User user = userService.findUserByUsername(principal.getName());
        List<Order> orders = orderService.findOrderByUserId(user);
        //List<Product> products = new ArrayList<>();
       // orders.stream().forEach(order -> order.getOrderItems().forEach(orderItem -> products.add(orderItem.getProduct())));
      //  model.addAttribute("products", products);
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);

        return "user-orders";
    }


    @GetMapping("/orderDetails/{id}")
    public String viewUserOrderItems(Model model, @PathVariable(value="id")Long id){

        Order order = orderService.findOrderById(id);
        List<OrderItem> orderItemList = order.getOrderItems();
        model.addAttribute("orderItems", orderItemList);
        model.addAttribute("order", order);

        return "user-order-details";
    }

    @GetMapping("/userInfo/{username}")
    public String showUserDetails(Model model, @PathVariable(value="username")String username){
        model.addAttribute("user", userService.findUserByUsername(username));
        return "user-details";
    }





}
