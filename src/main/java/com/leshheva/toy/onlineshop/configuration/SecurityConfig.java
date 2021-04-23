package com.leshheva.toy.onlineshop.configuration;

import com.leshheva.toy.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCustomAuthenticationSuccessHandler(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }


    @Override // хотим использовать свой собственный authenticationProvider
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

/*        http.httpBasic().and().authorizeRequests().antMatchers("/demo/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/toys/edit/**").hasRole("ADMIN")
                .*/

       /* http.httpBasic().and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .antMatchers("/toys/edit/**").hasRole("ADMIN")
                .antMatchers("/order/allOrders/").hasRole("ADMIN")
                .antMatchers("/order/edit/").hasRole("ADMIN")
             //   .antMatchers("/admin/**").hasRole("ADMIN")
            //    .antMatchers("/products/**").hasRole("ADMIN")
*//*                .antMatchers("/order/allOrders/").hasRole("ADMIN")
                .antMatchers("/order/edit/").hasRole("ADMIN")
                .antMatchers("/toys/edit/**").hasRole("ADMIN")
                .anyRequest().permitAll()*//*
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
*/


       http.authorizeRequests()
/*                .antMatchers("/toys/edit/**").hasRole("ADMIN")
                .antMatchers("/order/allOrders").hasRole("ADMIN")
                .antMatchers("/order/edit").hasRole("ADMIN")*/
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
               .and()
               .logout()
               .logoutSuccessUrl("/")
               .permitAll();


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                 "/images/**"
        );
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //натсравиваем свой authenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); // для получения деталей от пользователя мы используем свой UserService
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
