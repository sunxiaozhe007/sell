package com.sun.sell.controller;

import com.sun.sell.VO.ResultVO;
import com.sun.sell.converter.OrderForm2OrderDtoConverter;
import com.sun.sell.dto.OrderDto;
import com.sun.sell.enums.ResultEnum;
import com.sun.sell.exception.SellException;
import com.sun.sell.form.OrderForm;
import com.sun.sell.service.BuyerService;
import com.sun.sell.service.OrderService;
import com.sun.sell.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunxiaozhe
 * @time 2018/8/3 16:06
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;



    @RequestMapping(value = "/create",method = RequestMethod.POST)
    //创建订单
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderForm2OrderDtoConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto createResult = orderService.create(orderDto);
        Map<String ,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVOUtil.sucess(map);
    }
    //订单列表

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultVO<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0")Integer page,
                                         @RequestParam(value = "size",defaultValue = "10")Integer size)  {
        if (StringUtils.isEmpty(openid)){
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page,size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid,request);

        return ResultVOUtil.sucess(orderDtoPage.getContent());

    }

    //订单详情

    @GetMapping("/detail")
    public ResultVO<OrderDto> detail(@RequestParam("openid")String openid,
                                     @RequestParam("orderId")String orderId){

        OrderDto orderDto = buyerService.findOrderOne(openid, orderId);

        return ResultVOUtil.sucess(orderDto);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid")String openid,
                           @RequestParam("orderId")String orderId){

        buyerService.cancelOrder(openid, orderId);

        return ResultVOUtil.sucess();

    }

}
