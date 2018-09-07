package com.sun.sell.service.impl;

import com.sun.sell.domain.ProductCategory;
import com.sun.sell.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 11:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryService.findOne(3);
        System.out.println(productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategories = productCategoryService.findAll();
        for (ProductCategory productCategory : productCategories){
            System.out.println(productCategories);
        }
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategories = productCategoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4,5,8,9));
        for (ProductCategory productCategory : productCategories){
            System.out.println(productCategory);
        }
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(10);
        productCategory.setCategoryName("zhang");
        productCategoryService.save(productCategory);
    }

}