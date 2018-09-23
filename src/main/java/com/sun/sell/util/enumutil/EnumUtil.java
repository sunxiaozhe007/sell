package com.sun.sell.util.enumutil;

import com.sun.sell.enums.CodeEnum;

/**
 * @author sunxiaozhe
 * @time 2018/8/6 10:58
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
