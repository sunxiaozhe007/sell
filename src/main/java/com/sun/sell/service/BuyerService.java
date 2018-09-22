package com.sun.sell.service;

import com.sun.sell.dto.OrderDto;

/**
 * 买家
 * @author sunxiaozhe
 * @time 2018/8/3 20:00
 */
public interface BuyerService {

    //查询一个订单
    OrderDto findOrderOne(String openid,String orderId);


    //取消订单
    OrderDto cancelOrder(String openid,String orderId);
}
