package com.zx.distributed.middleware.zookeeper.rmi.demo;

import com.zx.distributed.middleware.zookeeper.rmi.client.core.ZXRmiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018/12/26
 */
public class ClientDemo {

  private static final Logger logger = LoggerFactory.getLogger(ClientDemo.class);

  public static void main(String[] args) {
    IUserInterface instance = ZXRmiClient.getInstance(IUserInterface.class);
    logger.info("------->{}", instance.say("你好"));
  }
}
