package controller;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

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
