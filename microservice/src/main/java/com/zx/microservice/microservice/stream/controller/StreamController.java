package com.zx.microservice.microservice.stream.controller;

import com.zx.microservice.microservice.stream.MyChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhouxin
 * @date 2018-12-08
 */
//@RestController
public class StreamController {

    private final static Logger logger = LoggerFactory.getLogger(StreamController.class);


    @Resource(name = MyChannel.MY_OUTPUT)
    private MessageChannel sendShopMessageChannel;

    @GetMapping("/sendMsg")
    public String sendShopMessage(String content) {
        boolean isSendSuccess = sendShopMessageChannel.
                send(MessageBuilder.withPayload(content).build());
        return isSendSuccess ? "发送成功" : "发送失败";
    }

    @StreamListener(MyChannel.MY_INPUT)
    public void receive(Message<String> message) {
        logger.info("--------->stream:{}",message.getPayload());
    }
}
