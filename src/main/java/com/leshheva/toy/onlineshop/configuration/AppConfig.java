package com.leshheva.toy.onlineshop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Configuration
@ComponentScan("com.leshheva.toy.onlineshop")
public class AppConfig implements WebMvcConfigurer {

    @Value("${upload.path}") // Существует ли директория для сохранения файлов. указываем спрингу что хотим получить переменную. Выдергивает из контекста значение
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("addResourceHandlers");
        registry.addResourceHandler("/img/**") // каждое обращение к серверу  /img/**
                .addResourceLocations("file:" + uploadPath + "/"); // будет перенаправлять все запросы по пути
     //   registry.addResourceHandler("/static/**")
      //          .addResourceLocations("classpath:/static/");
    }
}
