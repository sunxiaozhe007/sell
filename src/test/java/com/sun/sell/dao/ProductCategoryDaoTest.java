package com.sun.sell.dao;

import com.sun.sell.domain.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 10:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired ProductCategoryDao productCategoryDao;

    @Test
    public void findOneTest(){
        Optional<ProductCategory> productCategory = productCategoryDao.findById(6);
        System.out.println(productCategory);
    }

    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory = productCategoryDao.findById(6).get();
        productCategory.setCategoryName("张");
        productCategoryDao.save(productCategory);
    }


    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(8,9,10);
        List<ProductCategory> productCategories =  productCategoryDao.findByCategoryTypeIn(list);
        for (ProductCategory productCategory : productCategories){
            System.out.println(productCategory);
        }
    }

}