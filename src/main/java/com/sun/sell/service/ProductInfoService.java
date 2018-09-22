package com.sun.sell.service;

import com.sun.sell.domain.ProductInfo;
import com.sun.sell.dto.CarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 * @author sunxiaozhe
 * @time 2018/8/2 14:53
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在售的商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */
    void increateStock(List<CarDto> carDtoList);

    /**
     * 减库存
     */
    void decreateStock(List<CarDto> carDtoList);

    /**
     * 上架
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     */
    ProductInfo offSale(String productId);






}
