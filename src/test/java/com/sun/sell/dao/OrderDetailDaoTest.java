package com.sun.sell.dao;

import com.sun.sell.domain.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 19:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setDetailId("8888");
        orderDetail.setOrderId("111111111111111");
        orderDetail.setProductIcon("dasdasd");
        orderDetail.setProductId("2222222222222");
        orderDetail.setProductName("sdsd ");
        orderDetail.setProductPrice(new BigDecimal(888));
        orderDetail.setProductQuantity(2);
        orderDetailDao.save(orderDetail);

    }
    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList  = orderDetailDao.findByOrderId("444444");
        for (OrderDetail orderDetail : orderDetailList){
            System.out.println(orderDetail);
        }
    }
}