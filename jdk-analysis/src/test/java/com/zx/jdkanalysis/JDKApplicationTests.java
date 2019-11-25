package com.zx.jdkanalysis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class JDKApplicationTests {

  private static final Logger logger = LoggerFactory.getLogger(JDKApplicationTests.class);

  /**
   * 交换失效
   *
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   */
  @Test
  public void contextLoads() throws NoSuchFieldException, IllegalAccessException {
    Integer a = 1;
    Integer b = 2;
    swap(a, b);
    logger.info("a:{},b:{}", a, b);
    String c = "1c";
    String d = "2c";
    swap(c, d);
    logger.info("c:{},d:{}", c, d);
    Object object1 = new Object();
    Object object2 = new Object();
    logger.info("before object1:{},object2:{}", object1, object2);
//        swap(object1,object2);
    logger.info("before object1:{},object2:{}", object1, object2);
    int a1 = 1;
    int b1 = 2;
    swap(a1, b1);
    logger.info("before a1:{},b1:{}", a1, b1);

  }

  private void swap(Object a, Object b) {
    Object tmp = a;
    a = b;
    b = tmp;
  }

  private void swap(Integer a, Integer b) throws NoSuchFieldException, IllegalAccessException {
    Field field = Integer.class.getDeclaredField("value");
    field.setAccessible(true);
    int tmp = field.getInt(a);
    field.setInt(a, b);
    field.setInt(b, tmp);
  }


  /**
   * NaN 永远false
   */
  @Test
  public void test2() {
    double double1 = Double.NaN;
    logger.info("-------->,{}", double1 == double1);
  }

  /**
   * float double 溢出
   * 精度丢失
   */
  @Test
  public void test3() {
    // An example of overflow:
    double d = Double.MAX_VALUE;
    logger.info("overflow produces infinity: ");
    logger.info(d + "*10==" + d * 10);
    // An example of gradual underflow:
    d = Double.MIN_VALUE / Math.PI;
    logger.info("gradual underflow: " + d + "\n ");
    for (int i = 0; i < 4; i++)
      logger.info(" " + (d /= 100000));
    // An example of NaN:
    logger.info("0.0/0.0 is Not-a-Number: ");
    d = 0.0 / 0.0;
    logger.info("" + d);
    // An example of inexact results and rounding:
    logger.info("inexact results with float:");
    for (int i = 0; i < 100; i++) {
      float z = 1.0f / i;
      if (z * i != 1.0f)
        logger.info(" " + i);
    }
    // Another example of inexact results and rounding:
    logger.info("inexact results with double:");
    for (int i = 0; i < 100; i++) {
      double z = 1.0 / i;
      if (z * i != 1.0)
        logger.info(" " + i);
    }
    // An example of cast to integer rounding:
    logger.info("cast to int rounds toward 0: ");
    d = 12345.6;
    logger.info((int) d + " " + (int) (-d));
  }


}

