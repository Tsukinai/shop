package cn.edu.hit.dao;

import cn.edu.hit.po.Order;
import cn.edu.hit.po.OrderExt;
import cn.edu.hit.po.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    void addOrder(Order order);

    void addOrderItem(OrderItem orderItem);

    List<OrderExt> selallOrder(Integer getuId);

    List<Map<Long, Integer>> selState(Integer getuId);
}
