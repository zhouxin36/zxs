package com.zx.codeanalysis.pattern.factory.func;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018/10/16
 */
public class FactoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(FactoryTest.class);

  public static void main(String[] args) {
    LOGGER.info(new MengniuFactory().getMilk().toString());
  }
}
