package com.sun.sell.converter;

import com.sun.sell.domain.OrderMaster;
import com.sun.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sunxiaozhe
 * @time 2018/8/3 11:14
 */
public class OrderMaster2OrderDtoConverter {

    public static OrderDto convert(OrderMaster orderMaster){

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
