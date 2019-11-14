package com.zx.codeanalysis.mybatis.mybatisv2.plugin;

import com.zx.codeanalysis.mybatis.mybatisv2.plugin.annotation.ZXIntercepts;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.annotation.ZXSignature;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class ZXPlugin implements InvocationHandler {

    private Map<Class, Set<Method>> classSetMap;

    private ZXInterceptor interceptor;

    private Object target;

    private ZXPlugin(Map<Class, Set<Method>> classSetMap, ZXInterceptor interceptor, Object target) {
        this.classSetMap = classSetMap;
        this.interceptor = interceptor;
        this.target = target;
    }

    public static <T> T wrap(T target, ZXInterceptor interceptor){
        ZXIntercepts annotation = interceptor.getClass().getAnnotation(ZXIntercepts.class);
        if(annotation == null){
            throw new RuntimeException("该插件没有ZXIntercepts注解");
        }
        Map<Class, Set<Method>> classSetMap = new HashMap<>();
        ZXSignature[] value = annotation.value();
        Arrays.stream(value).forEach(e->{
            Set<Method> methods = classSetMap.computeIfAbsent(e.clazz(), k -> new HashSet<>());
            try {
                methods.add(e.clazz().getMethod(e.method(),e.args()));
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
        });

        Class<?>[] classes = Arrays.stream(target.getClass().getInterfaces()).filter(classSetMap::containsKey).toArray(Class<?>[]::new);

        if(classes.length > 0){
            //noinspection unchecked
            return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),classes,new ZXPlugin(classSetMap,interceptor,target));
        }
        return target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Set<Method> methods = classSetMap.get(method.getDeclaringClass());
        if (methods != null && !methods.isEmpty() && methods.contains(method)) {
            return interceptor.intercept(method,args,target);
        }

        return method.invoke(target, args);
    }
}
