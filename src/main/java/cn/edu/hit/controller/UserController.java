package cn.edu.hit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.edu.hit.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userservice;

    @RequestMapping("/test")
    public String test(){

        return null;
    }

}
