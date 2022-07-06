package cn.edu.hit.po;

import java.util.ArrayList;
import java.util.List;


public class CategoryExt extends Category{


    List<CategorySecond> list=new ArrayList<>();

    public List<CategorySecond> getList() {
        return list;
    }

    public void setList(List<CategorySecond> list) {
        this.list = list;
    }
}