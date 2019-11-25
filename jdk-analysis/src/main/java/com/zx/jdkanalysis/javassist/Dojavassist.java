package com.zx.jdkanalysis.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zhouxin
 * @since 2019/7/8
 */
public class Dojavassist {

  public static ClassPool pool = ClassPool.getDefault();

  public static void main(String[] args) throws Exception {
//        updateClass();
    System.out.println();
//        Class clazz = Class.forName("com.seventh7.widget.iedis.a.P");
//        Method method = clazz.getDeclaredMethod("a",int.class, int.class);
//        method.setAccessible(true);
//        method.invoke(null,9971, 30076);
  }

  private static void updateClass() throws NotFoundException, IOException, CannotCompileException {
    pool.insertClassPath("F:/iedis-3.2.jar");

    CtClass ctClass1 = pool.get("com.seventh7.widget.iedis.a.L");
    ctClass1.setModifiers(1);
    byte[] byteArray = ctClass1.toBytecode();
    FileOutputStream output = new FileOutputStream("D:\\L.class");
    output.write(byteArray);
  }
}