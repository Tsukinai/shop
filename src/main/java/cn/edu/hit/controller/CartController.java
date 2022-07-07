package cn.edu.hit.controller;

import cn.edu.hit.po.Cart;
import cn.edu.hit.po.CartItem;
import cn.edu.hit.po.Product;
import cn.edu.hit.po.User;
import cn.edu.hit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    HttpSession session;

    @Autowired
    ProductService productService;

    @RequestMapping("/tocart")
    @ResponseBody
    public String cart(Integer pId,Integer count){
        //1,验证用户是否登录
        User user = (User) session.getAttribute("user");
        if (user==null){
            return "no";
        }

        //2,查询商品信息
        Product product = productService.getProductById(pId);
        //3,封装购物项
        CartItem cartItem=new CartItem();
        cartItem.setProduct(product);
        cartItem.setCount(count);


        //4,获取session
        Cart cart = (Cart) session.getAttribute("cart");

        //5,判断session
        if (cart==null){
            //第一次添加购物车
            Cart c1=new Cart();
            c1.setMap(cartItem);
            session.setAttribute("cart",c1);
        }else {
            //不是一次添加购物车
            cart.setMap(cartItem);
        }


        return "ok";
    }


    @RequestMapping("/docart")
    public String tocart(){
        return "cart";
    }
    /**
     * @description:修改购物项数量
     * @author: dark
     * @date: 2022/6/10 19:12
     * @param: No such property: code for class: Script1
     * @return: 总计
     **/
    @RequestMapping("/changeCount")
    @ResponseBody
    public String changeCount(Integer pId,Integer count){
        //1，获取购物车
        Cart cart = (Cart) session.getAttribute("cart");

        //2,调用cart方法
        cart.changeCount(pId,count);
        return cart.getTotal().toString();
    }


    @RequestMapping("/delCartItem")
    @ResponseBody
    public String delCartItem(Integer pId){
        // Integer c类  存在堆区  有默认值   Integer -》（拆箱），int  -》运算
        // int         栈区     没有默认值
        //1，获取购物车
        Cart cart = (Cart) session.getAttribute("cart");

        //2,调用cart方法
        cart.delCartItem(pId);
        return cart.getTotal().toString();
    }

    @RequestMapping("/clear")
    @ResponseBody
    public String clear(){
        //1，获取购物车
        Cart cart = (Cart) session.getAttribute("cart");

        //2,调用cart方法
        cart.rvo();
        return null;
    }
}
