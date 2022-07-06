package cn.edu.hit.controller;

import cn.edu.hit.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.edu.hit.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        if(user.getUsername()==null|| user.getUsername().trim().equals("")){
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

    @RequestMapping("/dologin")
    public String dologin(User user, String checxBox, HttpSession session, HttpServletResponse response, Model model){
        User user1= userService.login(user);
        if (user1==null){
            //错误
            model.addAttribute("error","用户名或密码错误");
            return "login";
        }else {
            //正确
            //将用户信息存进域对象   pagecontext（当前页面有效）   request（一次请求）  session（一次会话）   senvletcontext（服务区）
            session.setAttribute("user",user1);
            //是否勾选记住密码
            if("on".equals(checxBox)){
                //记住密码
                Cookie cookie1=new Cookie("name",user1.getUsername());
                Cookie cookie2=new Cookie("pass",user1.getPassword());
                cookie1.setPath("/");
                cookie2.setPath("/");
                cookie1.setMaxAge(60*60*24*7);
                cookie2.setMaxAge(60*60*24*7);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }else {
                //不记住密码
                Cookie cookie1=new Cookie("name",user1.getUsername());
                Cookie cookie2=new Cookie("pass",user1.getPassword());
                cookie1.setPath("/");
                cookie2.setPath("/");
                cookie1.setMaxAge(0);
                cookie2.setMaxAge(0);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            // return "index";
            return "redirect:http://localhost:7070/shop/index/toindex";
        }
    }
}
