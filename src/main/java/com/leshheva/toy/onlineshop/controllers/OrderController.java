package com.leshheva.toy.onlineshop.controllers;


import com.leshheva.toy.onlineshop.entities.*;
import com.leshheva.toy.onlineshop.repositories.specifications.OrderSpecification;
import com.leshheva.toy.onlineshop.repositories.specifications.ProductSpecification;
import com.leshheva.toy.onlineshop.service.*;
import com.leshheva.toy.onlineshop.utils.OrderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
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


    @GetMapping("orders/allOrders")
    public String showAllOrders(Model model, @RequestParam(value = "date", required = false) String date,
                                             @RequestParam(value = "min", required = false) Double min){

        LocalDateTime localDateTime = null;
        Specification<Order> spec = Specification.where(null);
        model.addAttribute("date", date);
        if (date != null & date !="") {
           date = date + "T00:00";
           localDateTime = LocalDateTime.parse(date);
           spec = spec.and(OrderSpecification.creationDateGreaterThanOrEq(localDateTime));
        }
        if (min != null){
            spec = spec.and(OrderSpecification.priceGreaterThanOrEq(min));
        }
        model.addAttribute("min", min);
        model.addAttribute("orders", orderService.findAllWithFiltering(spec));
        return "orders-page";
    }

    @GetMapping("/orders/edit")
    public String showOrdersForEdit(Model model) {

        List<Order> orders = new ArrayList<>();
        orderService.findAllOrders().iterator().forEachRemaining(orders::add);
        model.addAttribute("wrappedOrders", new OrderWrapper(orders));
        model.addAttribute("statuses", orderStatusService.findAllStatuses());
        return "orders-edit";
    }

    @PostMapping("/orders/save")
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
        return "redirect:/orders/allOrders";
    }




    @PostMapping("/order/buy")
    public String registrateOrder(Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        orderService.createOrder(user, shoppingCartService.getOrderItems(), shoppingCartService.totalOrderPrice());
        return "redirect:/user/orders/" + user.getUsername();
    }


}
