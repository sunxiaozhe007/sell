package com.sun.sell.service.impl;
import com.sun.sell.converter.OrderMaster2OrderDtoConverter;
import com.sun.sell.dao.OrderDetailDao;
import com.sun.sell.dao.OrderMasterDao;
import com.sun.sell.domain.OrderDetail;
import com.sun.sell.domain.OrderMaster;
import com.sun.sell.domain.ProductInfo;
import com.sun.sell.dto.CarDto;
import com.sun.sell.dto.OrderDto;
import com.sun.sell.enums.OrderStatusEnum;
import com.sun.sell.enums.PayStatusEnum;
import com.sun.sell.enums.ResultEnum;
import com.sun.sell.exception.SellException;
import com.sun.sell.service.OrderService;
import com.sun.sell.service.ProductInfoService;
import com.sun.sell.service.WebSocket;
import com.sun.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
 import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sunxiaozhe
 * @time 2018/8/2 20:41
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private WebSocket webSocket;

    @Override
    public OrderDto create(OrderDto orderDto) {

        String orderId = KeyUtil.genUniqueKey();

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

    /*    List<CarDto> carDtoList = new ArrayList<>();*/

        //1.查询商品
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailDao.save(orderDetail);

           /* CarDto carDto = new CarDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
            carDtoList.add(carDto);*/
        }

        //3.写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);

        //4.扣库存
        List<CarDto> carDtoList = orderDto.getOrderDetailList().stream().map(e -> new CarDto(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreateStock(carDtoList);

        //发送webbsocket消息
        webSocket.sendMessage(orderDto.getOrderId());
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findById(orderId).get();
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid,pageable);

        List<OrderDto> orderDtoList = OrderMaster2OrderDtoConverter.convert(orderMasterPage.getContent());

        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtoList,pageable,orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_EXIST);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());

        BeanUtils.copyProperties(orderDto,orderMaster);

        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult == null){
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返还库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST.ORDER_DETAIL_EMPTY);
        }
        List<CarDto> carDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new CarDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increateStock(carDtoList);
        //如果已支付，需要退款
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TOdo
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {

        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_EXIST);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult == null){
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_EXIST);
        }
        //判断支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改订单支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult == null){
            throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findAll(pageable);

        List<OrderDto> orderDtoList = OrderMaster2OrderDtoConverter.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDtoList,pageable,orderMasterPage.getTotalElements());
    }
}
