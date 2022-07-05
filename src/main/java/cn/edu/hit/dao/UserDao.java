package cn.edu.hit.dao;

import org.apache.ibatis.annotations.Select;

public class UserDao {

    @Select("select count(*) from 'user'")
    int test(){
        return 0;
    };

}
