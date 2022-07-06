package cn.edu.hit.controller;

import cn.edu.hit.po.Product;
import cn.edu.hit.po.ProductExt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Category")
public class CategorySecondConrtoller {

    @RequestMapping("/toCategorySecond")
    public String toCategorySecond(ProductExt<Product> productExt, Model model){

        return "categorySecond";
    }
}