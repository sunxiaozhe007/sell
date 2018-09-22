package com.sun.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.sun.sell.dto.OrderDto;
import com.sun.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunxiaozhe
 * @time 2018/8/5 21:34
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {


    @Autowired
    private BestPayServiceImpl bestPayService;

    @Override
    public void create(OrderDto orderDto) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDto.getOrderId());
        payRequest.setOrderName("孙晓哲");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        bestPayService.pay(payRequest);


        log.info("【微信支付】request={}",payRequest);
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付response】={}",payResponse);

    }
}
