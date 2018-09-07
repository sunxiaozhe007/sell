package com.sun.sell.service.impl;

import com.sun.sell.domain.SellerInfo;
import com.sun.sell.service.SellerInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author sunxiaozhe
 * @time 2018/8/7 14:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {

    @Autowired
    private SellerInfoService sellerInfoService;

    private String OPENID="dsdd";

    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(OPENID);
        System.out.println(sellerInfo);
    }
}