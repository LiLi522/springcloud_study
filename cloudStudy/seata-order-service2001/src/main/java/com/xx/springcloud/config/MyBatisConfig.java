package com.xx.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.xx.springcloud.dao"})
public class MyBatisConfig {


}
