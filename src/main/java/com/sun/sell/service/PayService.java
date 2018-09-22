package com.sun.sell.service;

import com.sun.sell.dto.OrderDto;

/**
 * 支付
 * @author sunxiaozhe
 * @time 2018/8/5 21:32
 */

public interface PayService {

    void create(OrderDto orderDto);

}
