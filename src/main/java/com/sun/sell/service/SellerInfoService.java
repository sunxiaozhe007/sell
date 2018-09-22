package com.sun.sell.service;

import com.sun.sell.domain.SellerInfo;

/**
 * @author sunxiaozhe
 * @time 2018/8/7 14:31
 */
public interface SellerInfoService {

    /**
     * 根据openid查询卖家信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
