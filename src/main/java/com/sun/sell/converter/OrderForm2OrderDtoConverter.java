package com.sun.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.sell.domain.OrderDetail;
import com.sun.sell.dto.OrderDto;
import com.sun.sell.enums.ResultEnum;
import com.sun.sell.exception.SellException;
import com.sun.sell.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunxiaozhe
 * @time 2018/8/3 16:19
 */
public class OrderForm2OrderDtoConverter {
    public static OrderDto convert(OrderForm orderForm){

        Gson gson = new Gson();

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());

        }catch (Exception e){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }
}
