package com.sun.sell.dao;

import com.sun.sell.domain.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sunxiaozhe
 * @time 2018/8/7 11:09
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);
}
