package com.leshheva.toy.onlineshop.controllers;

import com.leshheva.toy.onlineshop.entities.Product;
import com.leshheva.toy.onlineshop.entities.User;
import com.leshheva.toy.onlineshop.repositories.UserRepository;
import com.leshheva.toy.onlineshop.repositories.specifications.ProductSpecification;
import com.leshheva.toy.onlineshop.service.CategoryService;
import com.leshheva.toy.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/toys")
public class ProductConroller {

    private ProductService productService;
    private CategoryService categoryService;


    @Value("${upload.path}") // Существует ли директория для сохранения файлов. указываем спрингу что хотим получить переменную. Выдергивает из контекста значение
    private String uploadPath;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public  String showProductDetails(Model model, @PathVariable(value="id")Long id){
        model.addAttribute("product", productService.findProductById(id));
        return "product-page";
    }
    @GetMapping("/category/{id}")
    public  String showProductsInCategory(Model model, @PathVariable(value="id") Long id, //@RequestParam(value = "category") Long id,//
                                          @RequestParam(value = "word", required = false) String word,
                                          @RequestParam(value = "min", required = false) Double min,
                                          @RequestParam(value = "max", required = false) Double max){

        Specification<Product> spec = Specification.where(null);
        StringBuilder filters = new StringBuilder();
        if (word != null) {
            spec = spec.and(ProductSpecification.titleContains(word));
            filters.append("&word=" + word);

        }
        if (min != null) {
            spec = spec.and(ProductSpecification.priceGreaterThanOrEq(min));
            filters.append("&min=" + min);

        }
        if (max != null) {
            spec = spec.and(ProductSpecification.priceLesserThanOrEq(max));
            filters.append("&max=" + max);
        }
        spec = spec.and(ProductSpecification.categoryIdEquals(categoryService.getCategoryById(id)));
        model.addAttribute("products", productService.getProductsWithFiltering(spec));
        model.addAttribute("filters", null);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("word", word);
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "products";
    }


    @GetMapping("/edit/{id}")
    public String editProduct(Model model,  @PathVariable(value="id")Long id){

        model.addAttribute("product",productService.findProductById(id));
        model.addAttribute("categories",categoryService.getAllCategories());
        return "edit-product";
    }
//    @GetMapping("/delete/{id}")
//    public String deleteProduct(Model model,  @PathVariable(value="id")Long id){
//        Long categoryId = productService.getProduct(id).getCategory().getId();
//        productService.deleteProductById(id);
//        return "redirect:/toys/category/"+categoryId;
//    }
    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product("toy.jpg"));
        model.addAttribute("categories",categoryService.getAllCategories());
        return "edit-product";
    }


    @PostMapping("/edit")
    public String showEditProductForm( @RequestParam("file") MultipartFile file, Model model,
                                       @Valid @ModelAttribute(value = "product")  Product product, BindingResult bindingResult
                                     ){

        if(bindingResult.hasErrors() ){
            model.addAttribute("categories",categoryService.getAllCategories());
            return "edit-product";
        }

        String resultFilename = null;

     if(file.isEmpty() == false){

         File uploadDir = new File(uploadPath);

         if (!uploadDir.exists()){
             System.out.println("mkdir");
             uploadDir.mkdirs();
         }

           String uuidFile = UUID.randomUUID().toString(); // создали уникальное имя файла обезапасили от колизий
           resultFilename = file.getOriginalFilename(); //uuidFile+"."+file.getOriginalFilename();

         try {
             file.transferTo(new File(uploadPath +"/" + resultFilename));
         } catch (IOException e) {
             e.printStackTrace();
         }
     }


        if (resultFilename == null){
            product.setPath_img(product.getPath_img());
        }
        else{
            product.setPath_img(resultFilename);
        }
        productService.saveProduct(product);
        Long categoryId = product.getCategory().getId();
        return "redirect:/toys/category/"+categoryId;
    }

}
