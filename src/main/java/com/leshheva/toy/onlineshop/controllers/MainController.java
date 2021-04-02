package com.leshheva.toy.onlineshop.controllers;

import com.leshheva.toy.onlineshop.configuration.Cat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/hello")
    @ResponseBody
    public String doSomething(){
        return "Hello world";
    }

    @GetMapping("/index/")
    public String doIndex(Model model, @RequestParam(value = "name") String name) { // автоматически спринг заинжектил модель. Модель помогает прокидывать данные в html
        //@RequestParam(value = "name") String name  в момент когда кто-то стучиться на индекс мы ожидаем параметр который назавается  name и кладем его в String name
        model.addAttribute("name", name); // это обычный хешмеп ключ значение
        return "index";
    }

    @GetMapping("/index2/{name}") // можем передывать параметр в путь
    public String doIndex2(Model model, @PathVariable(value = "name") String name) {
        model.addAttribute("name", name);
        return "index2";
    }

    @GetMapping("/cat")
    @ResponseBody
    public Cat catmethod() {
        return new Cat(1L,"Barsik", "red");
    }





    @GetMapping("/form") // когда кто-то посылает запрос за адрес /form мы ему показываем эту страничку
    public String showForm(){
        return "simple-form";

    }
    @PostMapping("/form") // сама simple-form при submit посылает пост запрос на то же самый адрес /form
    public String saveForm(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) { // и мы выдернем от туда параметры
        System.out.println(name);
        System.out.println(email);
        return "redirect:/cat";
    }




// то есть если задача создать объект через формы. Мы можем создать пустой объект
    // этот объект прокидываем в модель
    // сама cat-form говорит ага мне прилетел объект cat. Когда делваем сабмит то форма собирает объект и посылает его нам
    @GetMapping("/addcat")
    public String showAddCatForm(Model model){
        Cat cat = new Cat(2L, null,null);
        model.addAttribute("cat", cat);
        return "cat-form";
    }

    @PostMapping("/addcat")
    public String showAddCatForm(@ModelAttribute(value = "cat") Cat cat){
        System.out.println(cat.getId()+" "+ cat.getName()+" " + cat.getColor());
        return "redirect:/cat";
    }








}
