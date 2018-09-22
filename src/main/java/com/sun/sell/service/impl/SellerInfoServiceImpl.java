package com.sun.sell.service.impl;

import com.sun.sell.dao.SellerInfoDao;
import com.sun.sell.domain.SellerInfo;
import com.sun.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunxiaozhe
 * @time 2018/8/7 14:34
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }
}
