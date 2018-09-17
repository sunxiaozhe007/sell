package com.sun.sell.dao;

import com.sun.sell.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 19:30
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    /**
     * 根据orderId查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
