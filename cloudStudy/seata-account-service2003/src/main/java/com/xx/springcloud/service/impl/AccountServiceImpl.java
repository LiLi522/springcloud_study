package com.xx.springcloud.service.impl;

import com.xx.springcloud.dao.AccountDao;
import com.xx.springcloud.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------>account-service中扣减余额开始");
        accountDao.decrease(userId, money);
        LOGGER.info("------>account-service中扣减余额结束");
    }
}
