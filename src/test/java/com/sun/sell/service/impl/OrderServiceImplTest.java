package com.sun.sell.service.impl;

import com.sun.sell.domain.OrderDetail;
import com.sun.sell.dto.OrderDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 21:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "1111111111110";

    private final String ORDER_ID = "1533257890864604497";

    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("孙晓哲");
        orderDto.setBuyerAddress("宝鸡");
        orderDto.setBuyerPhone("187292399999");
        orderDto.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("hello");
        o1.setProductQuantity(10);
        orderDetailList.add(o1);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("rrrr");
        o2.setProductQuantity(10);
        orderDetailList.add(o2);
        orderDto.setOrderDetailList(orderDetailList);
        orderService.create(orderDto);
    }

    @Test
    public void findOne() {
        OrderDto result = orderService.findOne(ORDER_ID);
        System.out.println(result);
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDto> orderDtoPage = orderService.findList(BUYER_OPENID,request);
        System.out.println(orderDtoPage.getTotalElements());
        System.out.println(orderDtoPage.getTotalPages());
        System.out.println(orderDtoPage.getContent());
    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.cancel(orderDto);

    }

    @Test
    public void finish() {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.finish(orderDto);
    }

    @Test
    public void paid() {
        OrderDto orderDto = orderService.findOne(ORDER_ID);
        OrderDto result = orderService.paid(orderDto);
    }

    @Test
    public void list(){
        PageRequest request = new PageRequest(0,2);
        Page<OrderDto> orderDtoPage = orderService.findList(request);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }
}