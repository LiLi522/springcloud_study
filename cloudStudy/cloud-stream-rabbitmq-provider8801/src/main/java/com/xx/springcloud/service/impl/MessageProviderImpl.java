package com.xx.springcloud.service.impl;

/**
 * 不能使用hutool工具包下的UUID方法 不然接收到的消息显示有问题
 * 消费者1号，---------》接受到的消息：{"leastSignificantBits":-8832270078250038563,"mostSignificantBits":4123372299366319625}	    port：8802
 * 消费者1号，---------》接受到的消息：{"leastSignificantBits":-8407276894973599148,"mostSignificantBits":846621491713034556}	    port：8802
 * 消费者1号，---------》接受到的消息：{"leastSignificantBits":-5398574222103338134,"mostSignificantBits":1273655466196355635}	    port：8802
 * 消费者1号，---------》接受到的消息：{"leastSignificantBits":-7826444096809963226,"mostSignificantBits":1899250736983328135}	    port：8802
 * 消费者1号，---------》接受到的消息：{"leastSignificantBits":-8020015136426811091,"mostSignificantBits":-8952782286226568171}	    port：8802
 * **/
//import cn.hutool.core.lang.UUID;
import com.xx.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;


@EnableBinding(Source.class)    //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;   //消息发送管道

    @Override
    public String send() {
        UUID serial = UUID.randomUUID();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial：" + serial);
        return null;
    }
}
