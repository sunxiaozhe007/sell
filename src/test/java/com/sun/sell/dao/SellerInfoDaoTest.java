package com.sun.sell.dao;

import com.sun.sell.domain.SellerInfo;
import com.sun.sell.util.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.simpleframework.xml.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author sunxiaozhe
 * @time 2018/8/7 11:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao sellerInfoDao;


    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setPassword("admin");
        sellerInfo.setUsername("admin");
        sellerInfo.setOpenid("sunxiaozhe");
        sellerInfoDao.save(sellerInfo);

    }

    @Test
    public void findByOpenid() {
        System.out.println(sellerInfoDao.findByOpenid("dgdfgdfgfdgdfgdfg"));
    }
}