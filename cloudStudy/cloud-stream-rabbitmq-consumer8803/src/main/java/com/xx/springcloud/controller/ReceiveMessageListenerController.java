package com.xx.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
/**
 * 没加上(Sink.class)则报错
 * ***************************
 * APPLICATION FAILED TO START
 * ***************************
 *
 * Description:
 *
 * A component required a bean named 'input' that could not be found.
 *
 *
 * Action:
 *
 * Consider defining a bean named 'input' in your configuration.
 * **/
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者2号------->，接收消息：" + message.getPayload() + "\t  port：" + serverPort);
    }
}
