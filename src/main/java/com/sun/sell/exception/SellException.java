package com.sun.sell.exception;

import com.sun.sell.enums.ResultEnum;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 20:52
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum){

        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }

}
