package com.management.controllers;


import com.management.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/createProduct")
    public String createProduct(){
        return "CreateProduct";
    }



}
