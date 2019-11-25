package com.zx.jdkanalysis.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @date 2019/1/24
 */
public class ReflectDemo {

  private static final Logger logger = LoggerFactory.getLogger(ReflectDemo.class);

  public static void main(String... args) {
    try {
      Class<?> c = Class.forName("java.util.concurrent.ConcurrentNavigableMap");
      logger.info("Class:  {}", c.getCanonicalName());
      logger.info("Modifiers:  {}",
          Modifier.toString(c.getModifiers()));

      logger.info("Type Parameters:");
      TypeVariable[] tv = c.getTypeParameters();
      if (tv.length != 0) {
        logger.info("  ");
        for (TypeVariable t : tv)
          logger.info("{} ", t.getName());
      } else {
        logger.info("  -- No Type Parameters --");
      }

      logger.info("Implemented Interfaces:");
      Type[] intfs = c.getGenericInterfaces();
      if (intfs.length != 0) {
        for (Type intf : intfs)
          logger.info("  {}", intf.toString());
      } else {
        logger.info("  -- No Implemented Interfaces --");
      }

      logger.info("Inheritance Path:");
      List<Class> l = new ArrayList<Class>();
      printAncestor(c, l);
      if (l.size() != 0) {
        for (Class<?> cl : l)
          logger.info("  {}", cl.getCanonicalName());
        logger.info("");
      } else {
        logger.info("  -- No Super Classes --");
      }

      logger.info("Annotations:");
      Annotation[] ann = c.getAnnotations();
      if (ann.length != 0) {
        for (Annotation a : ann)
          logger.info("  {}", a.toString());
        logger.info("");
      } else {
        logger.info("  -- No Annotations --");
      }

      // production code should handle this exception more gracefully
    } catch (ClassNotFoundException x) {
      x.printStackTrace();
    }
  }

  private static void printAncestor(Class<?> c, List<Class> l) {
    Class<?> ancestor = c.getSuperclass();
    if (ancestor != null) {
      l.add(ancestor);
      printAncestor(ancestor, l);
    }
  }
}
