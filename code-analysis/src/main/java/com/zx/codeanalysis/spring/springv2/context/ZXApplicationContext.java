package com.zx.codeanalysis.spring.springv2.context;

import com.zx.codeanalysis.mybatis.mybatisv2.config.ZXConfiguration;
import com.zx.codeanalysis.mybatis.mybatisv2.config.ZXSqlSession;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.ZXInterceptorChain;
import com.zx.codeanalysis.spring.annotation.Autowried;
import com.zx.codeanalysis.spring.annotation.Controller;
import com.zx.codeanalysis.spring.annotation.Mapper;
import com.zx.codeanalysis.spring.annotation.Service;
import com.zx.codeanalysis.spring.springv2.aop.ZXAopConfig;
import com.zx.codeanalysis.spring.springv2.bean.ZXBeanDefinition;
import com.zx.codeanalysis.spring.springv2.bean.ZXBeanWrapper;
import com.zx.codeanalysis.spring.springv2.context.support.ZXBeanDefinitionReader;
import com.zx.codeanalysis.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhouxin
 * @date 2018-11-12
 */
public class ZXApplicationContext {

  private ZXBeanDefinitionReader reader;

  private Map<String, ZXBeanDefinition> beanDefinitionMap = new HashMap<>();

  private Map<String, Object> beanCacheMap = new HashMap<>();

  private Map<String, ZXBeanWrapper> wrapperMap = new HashMap<>();

  private ZXConfiguration configuration = new ZXConfiguration(new ZXInterceptorChain());

  private ZXSqlSession sqlSession = new ZXSqlSession(configuration);

  public ZXApplicationContext(String... classLocation) {
    refresh(classLocation);

  }

  public Map<String, ZXBeanDefinition> getBeanDefinitionMap() {
    return beanDefinitionMap;
  }

  public ZXBeanDefinitionReader getReader() {
    return reader;
  }

  public Map<String, Object> getBeanCacheMap() {
    return beanCacheMap;
  }

  public Map<String, ZXBeanWrapper> getWrapperMap() {
    return wrapperMap;
  }

  private void refresh(String[] classLocation) {

    this.reader = new ZXBeanDefinitionReader(classLocation);

    List<String> classPaths = reader.getClassPaths();

    loadClass(classPaths);

    doAutowried();
  }

  private void doAutowried() {
    this.beanDefinitionMap.forEach((k, v) -> {
      if (v.getLazy()) {
        return;
      }
      getBean(k);
    });
    this.wrapperMap.forEach((k, v) -> {
      Object originalInstance = v.getOriginalInstance();
      if (!originalInstance.getClass().isAnnotationPresent(Controller.class)
          && !originalInstance.getClass().isAnnotationPresent(Service.class)) {
        return;
      }
      Field[] declaredFields = originalInstance.getClass().getDeclaredFields();
      Arrays.stream(declaredFields).forEach(e -> {
        if (!e.isAnnotationPresent(Autowried.class)) {
          return;
        }
        Autowried annotation = e.getAnnotation(Autowried.class);
        String beanName;
        if (StringUtils.isBlank(annotation.value())) {
          beanName = SpringUtils.lowerFirstCase(e.getType().getSimpleName());
        } else {
          beanName = annotation.value();
        }
        e.setAccessible(true);
        try {
          Field declaredField = v.getWrapperInstance().getClass().getSuperclass().getDeclaredField(e.getName());
          declaredField.setAccessible(true);
          e.set(originalInstance, this.wrapperMap.get(beanName).getWrapperInstance());
          declaredField.set(v.getWrapperInstance(), this.wrapperMap.get(beanName).getWrapperInstance());
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      });
    });
  }

  public Object getBean(String k) {
    if (this.wrapperMap.containsKey(k)) {
      return this.wrapperMap.get(k).getWrapperInstance();
    }
    ZXBeanDefinition zxBeanDefinition = this.beanDefinitionMap.get(k);
    Object object = initCacheBean(k, zxBeanDefinition);

    ZXBeanWrapper beanWrapper = new ZXBeanWrapper(object, configuration, initAopConfig(zxBeanDefinition));
    this.wrapperMap.put(k, beanWrapper);
    return this.wrapperMap.get(k).getWrapperInstance();
  }

  private ZXAopConfig initAopConfig(ZXBeanDefinition zxBeanDefinition) {
    ZXAopConfig zxAopConfig = new ZXAopConfig();
    String pointCut = (String) reader.getMap().get("pointCut");
    String[] aspectBefore = ((String) reader.getMap().get("aspectBefore")).split("\\s");
    String[] aspectAfter = ((String) reader.getMap().get("aspectAfter")).split("\\s");

    try {
      Class<?> clazz = Class.forName(zxBeanDefinition.getClassName());
      Method[] declaredMethods = clazz.getDeclaredMethods();
      Pattern compile = Pattern.compile(pointCut);
      Class<?> aClass = Class.forName(aspectBefore[0]);
      Arrays.stream(declaredMethods).forEach(e -> {
        Matcher matcher = compile.matcher(e.toString());
        if (matcher.matches()) {
          try {
            zxAopConfig.put(e, aClass.getDeclaredConstructor().newInstance(), aClass.getMethod(aspectBefore[1]), aClass.getMethod(aspectAfter[1]));
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }
      });

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return zxAopConfig;
  }

  private Object initCacheBean(String key, ZXBeanDefinition zxBeanDefinition) {
    try {
      Object object = this.beanCacheMap.get(key);
      if (object != null) {
        return object;
      }
      object = this.beanCacheMap.get(zxBeanDefinition.getFactoryName());
      if (object != null) {
        this.beanCacheMap.put(key, object);
        return object;
      }
      Class<?> clazz = Class.forName(zxBeanDefinition.getClassName());
      if (clazz.isInterface() && clazz.isAnnotationPresent(Mapper.class)) {
        object = sqlSession.getMapper(clazz);
      } else {
        object = clazz.getDeclaredConstructor().newInstance();
      }
      this.beanCacheMap.put(key, object);
      return object;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private void loadClass(List<String> classPaths) {
    classPaths.forEach(e -> {
      try {
        Class<?> clazz = Class.forName(e);
        if (clazz.isInterface() && !clazz.isAnnotationPresent(Mapper.class)) {
          return;
        }
        ZXBeanDefinition zxBeanDefinition = reader.registerBean(e);
        if (zxBeanDefinition != null) {
          beanDefinitionMap.put(zxBeanDefinition.getFactoryName(), zxBeanDefinition);
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        Arrays.stream(interfaces).forEach(o ->
            beanDefinitionMap.put(SpringUtils.lowerFirstCase(o.getSimpleName()), zxBeanDefinition)
        );
      } catch (ClassNotFoundException e1) {
        e1.printStackTrace();
      }
    });
  }
}
