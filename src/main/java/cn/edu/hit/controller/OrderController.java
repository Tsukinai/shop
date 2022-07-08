package cn.edu.hit.controller;

import cn.edu.hit.po.*;
import cn.edu.hit.service.OrderService;
import cn.edu.hit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    ProductService productService;

    @Autowired
    HttpSession session;

    @Autowired
    OrderService service;

    //购物车订单
    @RequestMapping("/toverify")
    public String toverify() {
        return "verify";
    }

    //立即购买 订单
    @RequestMapping("/toverify1")
    public String toverify1(Integer pId, Model model) {
        Product productById = productService.getProductById(pId);
        model.addAttribute("product", productById);
        return "verify1";
    }

    @RequestMapping("/doPay")
    public String doPay(Order order, Model model) {
        //地址
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }
        Cart cart = (Cart) session.getAttribute("cart");

        Integer oId = service.addOrder(order, user, cart);
        model.addAttribute("oId", oId);
        session.setAttribute("cart", null);
        return "pay";
    }

    @RequestMapping("/doPay1")
    public String doPay1(Order order, Integer pId, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }
        Product productById = productService.getProductById(pId);
        CartItem cartItem = new CartItem();
        cartItem.setCount(1);
        cartItem.setProduct(productById);
        Cart cart = new Cart();
        cart.setMap(cartItem);
        Integer oId = service.addOrder(order, user, cart);
        model.addAttribute("oId", oId);
        return "pay";
    }

    @RequestMapping("/toMyOrder")
    public String toMyOrder(ProductExt<OrderExt> productExt, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login";
        }
        productExt.setPageSize(3);
        ProductExt<OrderExt> orderExtList = service.selallOrder(user.getuId(), productExt);

        Map<Long, Integer> map = service.selState(user.getuId());

        model.addAttribute("map",map);
        model.addAttribute("orderExt",orderExtList);
        return "myOrder";
    }
}
