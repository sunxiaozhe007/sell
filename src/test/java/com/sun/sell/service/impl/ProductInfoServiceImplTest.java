package com.sun.sell.service.impl;

import com.sun.sell.domain.ProductInfo;
import com.sun.sell.service.ProductInfoService;
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
 * @time 2018/8/2 15:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("sunxiaozhe");
        System.out.println(productInfo);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos = productInfoService.findUpAll();
        for (ProductInfo productInfo : productInfos){
            System.out.println(productInfo);
        }
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> page = productInfoService.findAll(request);
        System.out.println(page.getContent());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("hello");
        productInfo.setProductName("喝的");
        productInfo.setProductPrice(new BigDecimal(29.9));
        productInfo.setProductStock(200);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("sunxiaozhesadsd");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(9);
        productInfoService.save(productInfo);
    }

    @Test
    public void onSale(){
        productInfoService.onSale("sunxiaozhe");
    }

    @Test
    public void offSale(){
        productInfoService.offSale("sunxiaozhe");
    }

}