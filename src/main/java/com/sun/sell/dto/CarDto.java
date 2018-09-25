package com.sun.sell.dto;

import lombok.Data;

/**
 * 购物车
 * @author sunxiaozhe
 * @time 2018/8/2 21:21
 */
@Data
public class CarDto {

    //商品id
    private String productId;
    //数量
    private Integer productQuantity;

    public CarDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

