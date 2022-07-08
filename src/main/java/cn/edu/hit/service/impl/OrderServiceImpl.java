package cn.edu.hit.service.impl;

import cn.edu.hit.dao.OrderDao;
import cn.edu.hit.po.*;
import cn.edu.hit.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao dao;
    @Override
    public Integer addOrder(Order order, User user, Cart cart) {
        //生成订单 支付
        //事务 回滚
        order.setTotal(cart.getTotal());
        order.setOrderTime(new Date());
        order.setState(0);
        order.setuId(user.getuId());
        dao.addOrder(order);
        //持久化订单项
        for (CartItem cartItem:cart.getMap()){
            OrderItem orderItem=new OrderItem();
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubTotal(cartItem.getSubTotal());
            orderItem.setpId(cartItem.getProduct().getpId());
            orderItem.setoId(order.getoId());
            dao.addOrderItem(orderItem);
        }
        return order.getoId();
    }

    @Override
    public ProductExt<OrderExt> selallOrder(Integer getuId, ProductExt<OrderExt> productExt) {
        PageHelper.startPage(productExt.getPageNow(),productExt.getPageSize());
        List<OrderExt> orderExtList=dao.selallOrder(getuId);
        PageInfo<OrderExt> pageInfo=new PageInfo<>(orderExtList);
        long total=pageInfo.getTotal();
        int pages=pageInfo.getPages();

        productExt.setList(orderExtList);
        productExt.setPageCount((int)total);
        productExt.setRowCount(pages);
        return null;
    }

    @Override
    public Map<Long, Integer> selState(Integer getuId) {
        Map<Long,Integer> map=new HashMap<>();
        List<Map<Long,Integer>> maps=dao.selState(getuId);
        for(Map<Long,Integer> stringIntegerMap:maps){
            Number integer=stringIntegerMap.get("count(0)");
            map.put(stringIntegerMap.get("state").longValue(),integer.intValue());
        }
        return map;
    }
}
