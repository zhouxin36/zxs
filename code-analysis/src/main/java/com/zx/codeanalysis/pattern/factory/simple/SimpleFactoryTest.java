package com.zx.codeanalysis.pattern.factory.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018/10/16
 */
public class SimpleFactoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleFactoryTest.class);

  public static void main(String[] args) {
    LOGGER.info(new SimpleFactory().getMilk("蒙牛").toString());
  }
}
