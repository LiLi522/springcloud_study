package com.xx.springcloud.controller;

import com.xx.springcloud.domain.CommonResult;
import com.xx.springcloud.domain.Order;
import com.xx.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping(value = "/order/create")
    public CommonResult create(Order order) {

        orderService.create(order);
        return new CommonResult(200, "创建订单成功");

    }

}
