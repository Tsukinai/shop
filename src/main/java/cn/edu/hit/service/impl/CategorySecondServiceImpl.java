package cn.edu.hit.service.impl;

import cn.edu.hit.dao.CategorySecondDao;
import cn.edu.hit.po.Product;
import cn.edu.hit.po.ProductExt;
import cn.edu.hit.service.CategorySecondService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySecondServiceImpl implements CategorySecondService {

    @Autowired
    CategorySecondDao dao;


    /*
    cid =null
    csid =null
    pNAME="女"
    */

    @Override
    public ProductExt<Product> getCategorySecond(ProductExt<Product> productExt) { //cid  csid  当前页  条数
        //1:当前页   2，没页条数
        PageHelper.startPage(productExt.getPageNow(),productExt.getPageSize());

        //查询结果集 4481
        List<Product> sategorySecond = dao.getCategorySecond(productExt);

        //分页工具
        PageInfo<Product> page = new PageInfo<Product>(sategorySecond);

        //获取总条数
        long total = page.getTotal();

        //获取总页数
        int pages = page.getPages();

        //封装结果集
        productExt.setList(sategorySecond);
        productExt.setPageCount((int) total);
        productExt.setRowCount(pages);

        return productExt;
    }

    @Override
    public List<Product> getHot(ProductExt<Product> productExt) {
        return dao.getHot(productExt);
    }
}
