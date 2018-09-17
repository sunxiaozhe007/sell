package com.sun.sell.enums;

import lombok.Getter;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 19:17
 */
@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
