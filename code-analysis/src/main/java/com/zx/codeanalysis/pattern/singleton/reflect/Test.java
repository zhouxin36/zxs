package com.zx.codeanalysis.pattern.singleton.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author zhouxin
 * @since 2019/3/12
 */
public class Test {

  private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

  public static void main(String[] args) throws Exception {
//    singletonLock();
    singletonReflect();
  }

  private static void singletonLock() throws Exception {
    Singleton instance = Singleton.getInstance();
    Singleton instance2 = Singleton.getInstance();
    Class<?> clazz = Singleton.class;
    Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
    declaredConstructor.setAccessible(true);
    Singleton instance3 = (Singleton) declaredConstructor.newInstance();
    LOGGER.info("getInstance1:{},getInstance2:{},declaredConstructor:{}", instance, instance2, instance3);

//        logger.info("star---->st2:{},str4:{}",Singleton.str2,Singleton.str4);
    Field field = clazz.getDeclaredField("str2");
    field.setAccessible(true);
    field.set(instance, "22");
//        logger.info("end---->st2:{},str4:{}",Singleton.str2,Singleton.str4);
  }

  private static void singletonReflect() throws Exception {
    Class<?> clazz = TestEnum.class;
    Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class, int.class, String.class, String.class);
    declaredConstructor.setAccessible(true);
    Object o = declaredConstructor.newInstance("hehe", 1, "he", "haha");
    LOGGER.info("--------------id:{},userName:{}", ((TestEnum) o).getId(), ((TestEnum) o).getUserName());
  }
}
