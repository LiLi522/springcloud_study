package com.xx.springcloud.service.impl;

import com.xx.springcloud.dao.OrderDao;
import com.xx.springcloud.domain.Order;
import com.xx.springcloud.service.AccountService;
import com.xx.springcloud.service.OrderService;
import com.xx.springcloud.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    /**
     * 创建订单-》调用库存服务口枷库存-》调用账户服务扣减账户余额-》修改订单状态
     * 简单说：
     * 下订单->减库存->减余额->改状态
     * **/
    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class )
    public void create(Order order) {
        log.info("====Seata全局事务Id========》" + RootContext.getXID() +  " ====");
        log.info("----->开始新建订单");
        //step1：新建订单
        orderDao.create(order);

        log.info("----->订单微服务开始调用库存，做扣减");
        //step2：扣减库存
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减End");

        log.info("----->订单微服务开始调用账户，做扣减");
        //step3：扣减账户
        log.info("====Seata全局事务Id========》" + RootContext.getXID() +  " ====");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减End");

        //修改订单的状态 从0到1
        log.info("----->开始修改订单状态");
        orderDao.update(order.getUserId(), 0);
        log.info("----->修改订单状态End");

        log.info("----->下订单结束");
    }
}
