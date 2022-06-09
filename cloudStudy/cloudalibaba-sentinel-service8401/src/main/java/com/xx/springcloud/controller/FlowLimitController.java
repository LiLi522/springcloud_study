package com.xx.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping(value = "/testA")
    public String testA() {
        return "-------testA";
    }

    @GetMapping(value = "/testB")
    public String teatB() {
        return "-------teatB";
    }

    @GetMapping(value = "/testD")
    public String testD() {
        //暂停线程几秒钟
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("test D 测试RT");
        return "------testD";
    }

    @GetMapping(value = "/testE")
    public String testE() {
        log.info("testE 测试异常比例");
        int age = 10/0;
        return "------testE";
    }

    @GetMapping(value = "/testF")
    public String testF() {
        log.info("testF 测试异常数");
        int age = 10/0;
        return "------testF";
    }

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "-------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "-------deal_testHotKey";
    }
}
