package com.zx.spingbootmicrowebflux.stream.controller;

import com.zx.spingbootmicrowebflux.stream.config.MyChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouxin
 * @date 2018-12-08
 */
@RestController
public class StreamController {

  private static final Logger LOGGER = LoggerFactory.getLogger(StreamController.class);

  private final MyChannel myChannel;

  public StreamController(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") MyChannel myChannel) {
    this.myChannel = myChannel;
  }

  @GetMapping("/sendMyMsg")
  public String sendMyMessage(String message) {
    boolean isSendSuccess = myChannel.sendMyMessage().
        send(MessageBuilder.withPayload(message).build());
    return isSendSuccess ? "发送成功" : "发送失败";
  }

  @StreamListener(MyChannel.MY_INPUT)
  public void receiveMY(Message<String> message) {
    LOGGER.info("--------->stream:{}", message.getPayload());
  }

  @GetMapping("/sendZXMsg")
  public String sendZXMessage(String message) {
    boolean isSendSuccess = myChannel.sendZXMessage().
        send(MessageBuilder.withPayload(message).build());
    return isSendSuccess ? "发送成功" : "发送失败";
  }

  @StreamListener(MyChannel.ZX_INPUT)
  public void receiveZX(Message<String> message) {
    LOGGER.info("--------->stream:{}", message.getPayload());
  }
}
