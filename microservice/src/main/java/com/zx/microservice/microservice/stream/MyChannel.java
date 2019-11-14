package com.zx.microservice.microservice.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhouxin
 * @date 2018-12-08
 */
public interface MyChannel {

    /**
     * 发消息的通道名称
     */
    String MY_OUTPUT = "my_output";

    /**
     * 消息的订阅通道名称
     */
    String MY_INPUT = "my_input";

    /**
     * 发消息的通道
     *
     * @return
     */
    @Output(MY_OUTPUT)
    MessageChannel sendShopMessage();

    /**
     * 收消息的通道
     *
     * @return
     */
    @Input(MY_INPUT)
    SubscribableChannel recieveShopMessage();
}
