package com.zx.codeanalysis.spring.springv1.conetxt;

import com.zx.codeanalysis.spring.annotation.Autowried;
import com.zx.codeanalysis.spring.annotation.Controller;
import com.zx.codeanalysis.spring.annotation.Service;
import com.zx.codeanalysis.spring.springv1.aop.MyAopConfig;
import com.zx.codeanalysis.spring.springv1.bean.BeanDefinition;
import com.zx.codeanalysis.spring.springv1.bean.BeanPostProcessor;
import com.zx.codeanalysis.spring.springv1.bean.BeanWrapper;
import com.zx.codeanalysis.spring.springv1.conetxt.support.BeanDefinitionReader;
import com.zx.codeanalysis.spring.springv1.core.BeanFactory;
import com.zx.codeanalysis.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhouxin
 * @date 2018/10/25
 */
public class MyApplicationContext extends MyDefaultListableBeanFactory implements BeanFactory {

    private static Logger logger = LoggerFactory.getLogger(MyApplicationContext.class);
    private String[] configLocations;

    private BeanDefinitionReader reader;


    //用来保证注册式单例
    private Map<String, Object> beanCacheMap = new HashMap<>();

    //用来存储所有的被代理过的对象
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();

    public Map<String, Object> getBeanCacheMap() {
        return beanCacheMap;
    }

    public void setBeanCacheMap(Map<String, Object> beanCacheMap) {
        this.beanCacheMap = beanCacheMap;
    }

    //依赖注入，从这里开始，通过读取BeanDefinition中的信息
    //然后，通过反射机制创建一个实例并返回
    //Spring做法是，不会把最原始的对象放出去，会用一个BeanWrapper来进行一次包装
    //装饰器模式：
    //1、保留原来的OOP关系
    //2、我需要对它进行扩展，增强（为了以后AOP打基础）
    @Override
    public Object getBean(String beanName) {
        if(this.beanWrapperMap.containsKey(beanName)){
            return this.beanWrapperMap.get(beanName).getWrapperInstance();
        }
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);

        BeanPostProcessor beanPostProcessor = new BeanPostProcessor();
        Object instant = instantionBean(beanDefinition);
        if (null == instant) {
            return null;
        }
        beanPostProcessor.postProcessBeforeInitialization(instant, beanName);
        BeanWrapper beanWrapper = new BeanWrapper(instant);
        beanWrapper.setMyAopConfig(instantionAopConfig(beanDefinition));
        beanWrapper.setPostProcessor(beanPostProcessor);
        this.beanWrapperMap.put(beanName, beanWrapper);
        beanPostProcessor.postProcessAfterInitialization(instant, beanName);

//        populateBean(beanName, instant);

        return this.beanWrapperMap.get(beanName).getWrapperInstance();
    }

    private Object instantionBean(BeanDefinition beanDefinition) {
        Object instance = null;
        String beanClassName = beanDefinition.getBeanClassName();
        String beanName = beanDefinition.getFactoryBeanName();
        try {
            if (this.beanCacheMap.containsKey(beanName)) {
                instance = this.beanCacheMap.get(beanName);
            } else {
                Class<?> clazz = Class.forName(beanClassName);
                instance = clazz.getDeclaredConstructor().newInstance();
                this.beanCacheMap.put(beanName, instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private void refresh() {

        //定位
        this.reader = new BeanDefinitionReader(configLocations);

        //加载
        List<String> beanDefinitions = reader.loadBeanDefinitions();

        //注册
        doRegistry(beanDefinitions);

        //依赖注入
        doAutorited();

//        DemoController demoController = (DemoController) getBean("demoController");
//        logger.info("out:{}",demoController.index("1"));
    }

    private void doAutorited() {

        this.beanDefinitionMap.forEach((key,value) -> {
            if (!value.isLazyInit()) {
                Object object = getBean(key);
                logger.info("------------>|toString:{}",object.toString());
                logger.info("------------>|class:{}",object.getClass());
            }
        });

        this.beanWrapperMap.forEach((key, value) -> populateBean(key, value.getOriginalInstance(), value.getWrapperInstance()));

    }

    public void populateBean(String beanName, Object instance, Object wrapperInstance) {
        Class<?> clazz = instance.getClass();

        if (clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class)) {
            Field[] fields = clazz.getDeclaredFields();
            Arrays.stream(fields).forEach(o -> {
                Autowried autowried = o.getAnnotation(Autowried.class);
                if (autowried != null) {
                    String autowriedBeanName = autowried.value();
                    if ("".equals(autowriedBeanName)) {
                        autowriedBeanName = SpringUtils.lowerFirstCase(o.getType().getSimpleName());
                    }

                    try {
                        Field field = wrapperInstance.getClass().getSuperclass().getDeclaredField(o.getName());
                        field.setAccessible(true);
                        o.setAccessible(true);
                        logger.info("----------->instance:{}|beanWrapperMap:{}|autowriedBeanName:{}", instance, beanWrapperMap, autowriedBeanName);
                        o.set(instance, this.beanWrapperMap.get(autowriedBeanName).getWrapperInstance());
                        field.set(wrapperInstance, this.beanWrapperMap.get(autowriedBeanName).getWrapperInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public MyApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    private void doRegistry(List<String> beanDefinitions) {
        beanDefinitions.forEach(e -> {
            try {
                Class<?> clazz = Class.forName(e);

                //如果是一个接口，是不能实例化的
                //用它实现类来实例化
                if (clazz.isInterface()) {
                    return;
                }

                BeanDefinition beanDefinition = reader.registerBean(e);
                if (beanDefinition != null) {
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
                }

                Class<?>[] interfaces = clazz.getInterfaces();
                for (Class<?> i : interfaces) {
                    //如果是多个实现类，只能覆盖
                    //为什么？因为Spring没那么智能，就是这么傻
                    //这个时候，可以自定义名字
                    this.beanDefinitionMap.put(SpringUtils.lowerFirstCase(i.getSimpleName()), beanDefinition);
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[0]);
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }

    private MyAopConfig instantionAopConfig(BeanDefinition beanDefinition) {
        MyAopConfig myAopConfig = new MyAopConfig();
        String pointCut = reader.getConfig().getProperty("pointCut");
        String[] aspectBefore = reader.getConfig().getProperty("aspectBefore").split("\\s");
        String[] aspectAfter = reader.getConfig().getProperty("aspectAfter").split("\\s");

        String className = beanDefinition.getBeanClassName();
        try {
            Class<?> clazz = Class.forName(className);
            Pattern compile = Pattern.compile(pointCut);

            Class<?> aClass = Class.forName(aspectBefore[0]);

            for (Method method :
                    clazz.getMethods()) {
                Matcher matcher = compile.matcher(method.toString());
                if (matcher.matches()) {
                    myAopConfig.put(method, aClass.getDeclaredConstructor().newInstance(), new Method[]{aClass.getMethod(aspectBefore[1]), aClass.getMethod(aspectAfter[1])});
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return myAopConfig;
    }
}
