package com.sun.sell.service.impl;

import com.sun.sell.dto.OrderDto;
import com.sun.sell.service.OrderService;
import com.sun.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author sunxiaozhe
 * @time 2018/8/6 8:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {

        OrderDto orderDto = orderService.findOne("1533257890864604497");
        payService.create(orderDto);
    }
}