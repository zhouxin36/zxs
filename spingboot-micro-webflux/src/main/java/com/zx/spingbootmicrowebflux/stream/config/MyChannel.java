package com.zx.spingbootmicrowebflux.stream.config;

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
   * 发消息的通道名称
   */
  String ZX_OUTPUT = "zx_output";
  /**
   * 消息的订阅通道名称
   */
  String ZX_INPUT = "zx_input";

  /**
   * 发消息的通道
   *
   * @return
   */
  @Output(MY_OUTPUT)
  MessageChannel sendMyMessage();

  /**
   * 收消息的通道
   *
   * @return
   */
  @Input(MY_INPUT)
  SubscribableChannel recieveMyMessage();

  /**
   * 发消息的通道
   *
   * @return
   */
  @Output(ZX_OUTPUT)
  MessageChannel sendZXMessage();

  /**
   * 收消息的通道
   *
   * @return
   */
  @Input(ZX_INPUT)
  SubscribableChannel recieveZXMessage();
}
