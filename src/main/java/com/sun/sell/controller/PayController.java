package com.sun.sell.controller;

import com.sun.sell.dto.OrderDto;
import com.sun.sell.enums.ResultEnum;
import com.sun.sell.exception.SellException;
import com.sun.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 支付
 * @author sunxiaozhe
 * @time 2018/8/5 20:41
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId")String orderId,
                      @RequestParam("returnUrl")String returnUrl){

        //1.查询订单
        OrderDto orderDto = orderService.findOne(orderId);
        if (orderDto == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.发起支付

    }


}
