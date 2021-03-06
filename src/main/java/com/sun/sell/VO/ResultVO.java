package com.sun.sell.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * @author sunxiaozhe
 * @time 2018/8/2 15:36
 */
@Data
public class ResultVO<T>{

    //错误码
    private Integer code;
    //提示信息
    private String msg;
    //具体内容
    private T data;
}
