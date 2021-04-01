package com.leshheva.toy.onlineshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/hello")
    @ResponseBody
    public String doSomething(){
        return "Hello world";
    }

}
