package com.xx.springcloud.service.impl;

import com.xx.springcloud.dao.AccountDao;
import com.xx.springcloud.service.AccountService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------>account-service中扣减余额开始");
        LOGGER.info("====Seata全局事务Id========》" + RootContext.getXID() +  " ====");
        //模拟超时异常，全局事务回滚
        //暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decrease(userId, money);
        LOGGER.info("------>account-service中扣减余额结束");
    }
}
