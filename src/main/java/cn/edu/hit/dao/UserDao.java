package cn.edu.hit.dao;

import cn.edu.hit.po.User;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    int test();

    @Select("select count(0) from `user` where username=#{name}")
    int changeName(String name);

    void addUser(User user);
}

