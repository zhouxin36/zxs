package com.zx.jdkanalysis.subtyping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 父子类型
 *
 * @author zhouxin
 * @date 2019/1/17
 */
public class SubtypingTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(SubtypingTest.class);

  public static void main(String[] args) {
    arraySub();
    LOGGER.info("----------------------------------------这是一条华丽的分割线-------------------------------------------------");
    primitiveSub();
  }

  private static void primitiveSub() {
    LOGGER.info("------------->{}", Integer.TYPE.isAssignableFrom(int.class));
    LOGGER.info("------------->{}", int.class.isPrimitive());
  }

  @SuppressWarnings("ConstantConditions")
  private static void arraySub() {
    LOGGER.info("Object > Object[] ------{}", Object.class.isAssignableFrom(Object[].class));
    LOGGER.info("Object > Object[] ------{}", new Object[]{} instanceof Object);

    LOGGER.info("Cloneable > Object[] ------{}", Cloneable.class.isAssignableFrom(Object[].class));
    LOGGER.info("Cloneable > Object[] ------{}", new Object[]{} instanceof Cloneable);

    LOGGER.info("java.io.Serializable > Object[] ------{}", java.io.Serializable.class.isAssignableFrom(Object[].class));
    LOGGER.info("java.io.Serializable > Object[] ------{}", new Object[]{} instanceof java.io.Serializable);

    LOGGER.info("Object > Integer[] ------{}", Object.class.isAssignableFrom(Integer[].class));
    LOGGER.info("Object > Integer[] ------{}", new Integer[]{} instanceof Object);

    LOGGER.info("Cloneable > Integer[] ------{}", Cloneable.class.isAssignableFrom(Integer[].class));
    LOGGER.info("Cloneable > Integer[] ------{}", new Integer[]{} instanceof Cloneable);

    LOGGER.info("Object[] > Integer[] ------{}", Object[].class.isAssignableFrom(Integer[].class));
    LOGGER.info("Object[] > Integer[] ------{}", new Integer[]{} instanceof Object[]);


    LOGGER.info("Number[] > Integer[] ------{}", Number[].class.isAssignableFrom(Integer[].class));
    LOGGER.info("Number[] > Integer[] ------{}", new Integer[]{} instanceof Number[]);

    System.out.println("111111111"+(new Integer[]{} instanceof Number[]));
    double[] doubles = {'0'};
    LOGGER.info("--------------->doubles class:{}", doubles.getClass());
    LOGGER.info("Object > primitive type ------{}", Object.class.isAssignableFrom(double[].class));
    LOGGER.info("Object > primitive type ------{}", doubles instanceof Object);

    LOGGER.info("Object[] > primitive type ------{}", Object[].class.isInstance(double[].class));

    LOGGER.info("Cloneable > primitive type ------{}", Cloneable.class.isAssignableFrom(double[].class));
    LOGGER.info("Cloneable > primitive type ------{}", doubles instanceof Cloneable);

    LOGGER.info("java.io.Serializable > primitive type ------{}", java.io.Serializable.class.isAssignableFrom(double[].class));
    LOGGER.info("java.io.Serializable > primitive type ------{}", doubles instanceof java.io.Serializable);
  }
}