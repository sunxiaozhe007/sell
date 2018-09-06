package com.sun.sell.dao;

import com.sun.sell.domain.OrderMaster;
import org.apache.catalina.LifecycleState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 19:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private final String openid = "1110203203";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("sunxiaozhettttt");
        orderMaster.setBuyerName("孙晓哲");
        orderMaster.setBuyerPhone("1111111111");
        orderMaster.setBuyerAddress("西安");
        orderMaster.setBuyerOpenid(openid);
        orderMaster.setOrderAmount(new BigDecimal(999));
        orderMasterDao.save(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0,3);
        Page<OrderMaster> orderMasters = orderMasterDao.findByBuyerOpenid(openid,request);
        List<OrderMaster> orderMasterList = orderMasters.getContent();
        for (OrderMaster orderMaster : orderMasterList){
            System.out.println(orderMaster);
        }

    }
}