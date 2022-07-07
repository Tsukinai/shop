package cn.edu.hit.service;

import cn.edu.hit.po.Product;
import cn.edu.hit.po.ProductExt;

import java.util.List;

public interface CategorySecondService {
    ProductExt<Product> getCategorySecond(ProductExt<Product> productExt);

    List<Product> getHot(ProductExt<Product> productExt);
}
