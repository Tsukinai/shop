package cn.edu.hit.po;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    //      k:商品id   v:购物项
    private Map<Integer,CartItem> map=new LinkedHashMap<>();
    private Double total=0.0;

    public Collection<CartItem> getMap() {
        //当想要购物项是不返回pid
        return map.values();
    }

    public void setMap(CartItem cartItem) {
        //判断你这购物项是否在购物车有没有
        Integer pId = cartItem.getProduct().getpId();
        if(map.containsKey(pId)){
            CartItem cartItem1 = map.get(pId);
            cartItem1.setCount(cartItem1.getCount()+cartItem.getCount());
        }else {
            //购物车里没有这个购物项
            map.put(pId,cartItem);
        }
        //更改总计
        total=total+cartItem.getSubTotal();
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @description:修改购物项数量
     * @author: dark
     * @date: 2022/6/10 19:12
     * @param: No such property: code for class: Script1
     * @return: 总计
     **/
    public void changeCount(Integer pId, Integer count) {
        //1获取对应的购物项
        CartItem cartItem = map.get(pId);
        //2,删掉小计
        total=total-cartItem.getSubTotal();
        //3,修改
        cartItem.setCount(count);
        //4,修改小计
        total=total+cartItem.getSubTotal();

    }

    //删除方法
    public void delCartItem(Integer pId) {
        CartItem remove = map.remove(pId);
        total=total-remove.getSubTotal();

    }

    public void rvo() {
        //
        map.clear();
        total=0.0;
    }
}
