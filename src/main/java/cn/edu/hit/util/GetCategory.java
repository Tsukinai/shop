package cn.edu.hit.util;

import cn.edu.hit.po.CategoryExt;
import cn.edu.hit.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.List;

public class GetCategory {
    @Autowired
    IndexService indexService;

    @Autowired
    ServletContext context;

    @PostConstruct
    public void get() {
        List<CategoryExt> category = indexService.getCategory();
        context.setAttribute("category", category);
    }

}
