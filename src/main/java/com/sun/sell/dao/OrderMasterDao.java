package com.sun.sell.dao;

import com.sun.sell.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 19:27
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

    /**
     * 根据买家openid查询订单并分页
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
