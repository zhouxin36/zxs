package com.zx.spingbootmicrowebflux;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpingbootApplicationTests {


  public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    Class<?> aClass = Class.forName("jdk.internal.loader.ClassLoaders");
    Field appClassLoader = Arrays.stream(aClass.getDeclaredFields()).filter(e ->
        e.getType().getName().contains("AppClassLoader")
    ).findAny().orElseGet(null);
    System.out.println(appClassLoader);
  }
}
