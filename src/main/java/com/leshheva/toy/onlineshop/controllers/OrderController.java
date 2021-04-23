package com.leshheva.toy.onlineshop.controllers;


import com.leshheva.toy.onlineshop.entities.*;
import com.leshheva.toy.onlineshop.service.*;
import com.leshheva.toy.onlineshop.utils.OrderWrapper;
import lombok.var;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/order")
public class OrderController {

    ShoppingCartService shoppingCartService;
    ProductService productService;
    OrderService orderService;
    UserService userService;
    OrderStatusService orderStatusService;

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

    @Autowired
    public void setOrderStatusService(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping("/order/create")
    public String createOrder(Model model, Principal principal) {

        User user = userService.findUserByUsername(principal.getName());
        if (shoppingCartService.totalOrderPrice() == 0) {
            return "redirect:/user/orders/" + user.getUsername();
        }
        model.addAttribute("user", user);
        model.addAttribute("order", new Order());
        model.addAttribute("orderItems", shoppingCartService.getOrderItems());
        model.addAttribute("totalPrice", shoppingCartService.totalOrderPrice());
        return "order-purchase";
    }

/*@Secured(value = {"ADMIN"})*/
    @GetMapping("order/allOrders")
    public String showAllOrders(Model model){

        model.addAttribute("orders", orderService.findAllOrders());
        return "orders-page";
    }

    @GetMapping("/order/edit")
    public String showOrdersForEdit(Model model) {

        List<Order> orders = new ArrayList<>();
        orderService.findAllOrders().iterator().forEachRemaining(orders::add);
        model.addAttribute("wrappedOrders", new OrderWrapper(orders));
        model.addAttribute("statuses", orderStatusService.findAllStatuses());
        return "orders-edit";
    }


    @PostMapping("/order/save")
    public String saveOrders(@ModelAttribute(value="wrappedOrders") OrderWrapper wrappedOrders, Model model) {

        List<Order> oldOrders = new ArrayList<>();
        List<Order> newOrdersValues =new ArrayList<>(wrappedOrders.getOrders());
        orderService.findAllOrders().iterator().forEachRemaining(oldOrders::add);


        for (Order order: newOrdersValues
             ) {

            oldOrders.stream().filter(oldOrder -> oldOrder.getId() == order.getId() && oldOrder.getOrderStatus().getId() != order.getOrderStatus().getId())
                      .forEach(oldOrder->oldOrder.setOrderStatus(order.getOrderStatus()));
        }
        orderService.saveOrders(oldOrders);
        return "redirect:/order/allOrders";
    }


    @PostMapping("/order/buy")
    public String registrateOrder(Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        orderService.createOrder(user, shoppingCartService.getOrderItems(), shoppingCartService.totalOrderPrice());
        return "redirect:/user/orders/" + user.getUsername();
    }


}
