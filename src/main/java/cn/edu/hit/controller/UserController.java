package cn.edu.hit.controller;

import cn.edu.hit.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.edu.hit.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/toregister")
    public String toregister() {
        return "register";
    }

    @RequestMapping("/changeName")
    @ResponseBody
    public String changeName(String name) {
        int i = userService.changeName(name);
        if (i == 0) {
            return "ok";
        } else {
            return "no";
        }
    }

    @RequestMapping("/doregister")
    public String doregister(User user, Model model){
        //用户名去掉空格
        if(user.getUsername()==null||user.getUsername().trim()==""){
            model.addAttribute("error","注册失败");
            return "register";
        }

        //验证密码
        String pattern = "^(?=.*[a-zA-Z])(?=.*[\\d])(?=.*[\\W])[a-zA-Z\\d\\W]{6,16}$";
        if (!user.getPassword().matches(pattern)){
            model.addAttribute("error","注册失败");
            return "register";
        }

        //验证手机号
        String phontPatter="^1[3,5,7,8][\\d]{9}$";
        if (!user.getPhone().matches(phontPatter)){
            model.addAttribute("error","注册失败");
            return "register";
        }
        //添加
        userService.addUser(user);
//        return "ok";
        //重定向  跳转登录页
//        return "login";
        return "redirect:http://localhost:7070/shop/user/tologin";
    }
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }
}
