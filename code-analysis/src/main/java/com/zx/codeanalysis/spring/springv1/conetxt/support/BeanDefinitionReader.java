package com.zx.codeanalysis.spring.springv1.conetxt.support;

import com.zx.codeanalysis.spring.springv1.bean.BeanDefinition;
import com.zx.codeanalysis.utils.SpringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * 对配置文件定位查找，读取，解析
 *
 * @author zhouxin
 * @date 2018/10/25
 */
public class BeanDefinitionReader {

  private Properties config = new Properties();

  private List<String> registyBeanClasses = new ArrayList<>();

  public BeanDefinitionReader(String... locations) {
    try (InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream(locations[0]
            .replace("classpath:", ""))) {
      config.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    doScanner(config.getProperty("scanPackage"));
  }

  public BeanDefinition registerBean(String className) {
    if (this.registyBeanClasses.contains(className)) {
      BeanDefinition beanDefinition = new BeanDefinition();
      beanDefinition.setBeanClassName(className);
      beanDefinition.setFactoryBeanName(SpringUtils.lowerFirstCase(className.substring(className.lastIndexOf(".") + 1)));
      return beanDefinition;
    }
    return null;
  }

  public List<String> loadBeanDefinitions() {
    return registyBeanClasses;
  }

  public Properties getConfig() {
    return this.config;
  }

  private void doScanner(String scanPackage) {

    URL url = this.getClass().getClassLoader()
        .getResource("/" + scanPackage.replaceAll("\\.", "/"));

    File file;
    if (url != null) {
      file = new File(url.getFile());
      Optional.ofNullable(file.listFiles()).map(Arrays::stream).ifPresent(e ->
          e.forEach(o -> {
            if (o.isDirectory()) {
              doScanner(scanPackage + "." + o.getName());
            } else {
              if (!o.getName().contains(".java")) {
                registyBeanClasses
                    .add(scanPackage + "." + o.getName()
                        .replace(".class", ""));
              }
            }
          })
      );
    }
  }
}
