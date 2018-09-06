package com.sun.sell.dao;

import com.sun.sell.domain.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 14:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("rrrr");
        productInfo.setProductName("孙晓哲");
        productInfo.setProductPrice(new BigDecimal(9.9));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("http://sunxiaozhe");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(8);
        productInfoDao.save(productInfo);
    }

    @Test
    public void findByProductStatus() {

        List<ProductInfo> productInfos = productInfoDao.findByProductStatus(1);
        for (ProductInfo productInfo : productInfos){
            System.out.println(productInfo);
        }
    }
}