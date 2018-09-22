package com.sun.sell.service;

import com.sun.sell.domain.ProductCategory;

import java.util.List;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 11:29
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
