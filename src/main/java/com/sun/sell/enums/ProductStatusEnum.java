package com.sun.sell.enums;

import lombok.Getter;
/**
 * 商品状态
 * @author sunxiaozhe
 * @time 2018/8/2 15:10
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0,"在售"),
    DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

}
