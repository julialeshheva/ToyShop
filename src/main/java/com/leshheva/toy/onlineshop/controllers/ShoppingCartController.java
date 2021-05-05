package com.leshheva.toy.onlineshop.controllers;

import antlr.StringUtils;
import com.leshheva.toy.onlineshop.service.ShoppingCartService;
import com.leshheva.toy.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("toys/cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCart;

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    @GetMapping
    public String cartPage(Model model) {
        model.addAttribute("cart", shoppingCart.getOrderItems());
        model.addAttribute("totalPrice", shoppingCart.totalOrderPrice());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        shoppingCart.add(id);
        String referrer = httpServletRequest.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
       shoppingCart.removeProduct(id);
        String referrer = httpServletRequest.getHeader("referer");

        return "redirect:/toys/cart/";
    }

}
