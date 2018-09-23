package com.sun.sell.util;

import com.sun.sell.VO.ResultVO;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 16:56
 */
public class ResultVOUtil {

    public static ResultVO sucess(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
    public static ResultVO sucess(){
        return sucess(null);
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;

    }
}
