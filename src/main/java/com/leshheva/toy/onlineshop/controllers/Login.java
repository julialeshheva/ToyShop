package com.leshheva.toy.onlineshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class Login {

    @GetMapping("/login")
    public String showMyLoginPage() {
        return "login";
    }

}
