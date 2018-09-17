package com.sun.sell.enums;

import lombok.Getter;

/**
 * 订单枚举
 * @author sunxiaozhe
 * @time 2018/8/2 19:11
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

}
