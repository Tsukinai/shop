package cn.edu.hit.controller;

import cn.dsna.util.images.ValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/val")
public class CodeController {

    @RequestMapping("/Code")
    public void getCode(HttpServletResponse response, HttpSession httpSession) {

        try {
            //1,生成验证码图片     1,宽   2，高   3，字母个数    4：干扰线
            ValidateCode validateCode = new ValidateCode(164, 65, 4, 100);
            //1.1纪录图答案
            String code = validateCode.getCode();
            //1.2存进session域对象
            httpSession.setAttribute("code", code);
            //2,返回前端页面
            validateCode.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/checkValidate")
    @ResponseBody
    public String checkValidate(String code, HttpSession httpSession) {
        String code1 = (String) httpSession.getAttribute("code");
        if (code1.equalsIgnoreCase(code)) {
            return "ok";
        } else {
            return "no";
        }
    }
}