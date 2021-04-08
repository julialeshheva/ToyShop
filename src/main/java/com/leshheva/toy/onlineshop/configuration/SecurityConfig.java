package com.leshheva.toy.onlineshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
 //первый варинат настройки источника данных для аутентификации - брать данные из БД
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // (1)
        auth.jdbcAuthentication().dataSource(dataSource);
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // (2)
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(users.username("user1").password("pass1").roles("USER", "ADMIN"))
                .withUser(users.username("user2").password("pass2").roles("USER"));
    }*/

    //Прописать правила доступа. Ка
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll() //абсолютно любые запросы в веб приложении разрешены всем пользователям
              .antMatchers("/secured/**").hasAnyRole("ADMIN") // если у нас есть какой-то блок сайта , начинающего с /secured/ то мы можем его полностью защитить
                .and()
                .formLogin()// стандартная форма логина от спринг
            //    .loginPage("/login") пишем название формы . Форма живет по адресу /login
            //    .loginProcessingUrl("/authenticateTheUser") Когда пользователь вобьет свой логин пароль, то куда эта форма пошлет пост запрос с логин и пароль
                .permitAll();
    }


}
