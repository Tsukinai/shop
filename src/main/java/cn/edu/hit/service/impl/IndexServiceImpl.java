package cn.edu.hit.service.impl;

import cn.edu.hit.dao.CategorySecondDao;
import cn.edu.hit.dao.IndexDao;
import cn.edu.hit.po.Category;
import cn.edu.hit.po.CategoryExt;
import cn.edu.hit.po.CategorySecond;
import cn.edu.hit.po.Product;
import cn.edu.hit.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    IndexDao indexDao;

    @Override
    public List<CategoryExt> getCategory() {
        List<CategoryExt> list= indexDao.getCategory();
        return list;
    }

    @Override
    public Map<Integer, List<Product>> getProduct(List<CategoryExt> category) {
        Map<Integer, List<Product>> map=new HashMap<>();
        for(CategoryExt categoryExt:category){
            for(CategorySecond categorySecond: categoryExt.getList()){
                List<Product> productList=indexDao.getProduct(categorySecond.getCsId());
                map.put(categorySecond.getCsId(),productList);
            }
        }
        return map;
    }
}

