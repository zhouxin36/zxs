package com.zx.codeanalysis.mybatis.mybatisv1.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhouxin
 * @date 2018/10/23
 */
public class MyPlugin implements InvocationHandler {

    private Object target;
    private MyInterceptor interceptor;
    private Map<Class<?>, Set<Method>> signatureMap;

    private MyPlugin(Object target, MyInterceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) {
        this.target = target;
        this.interceptor = interceptor;
        this.signatureMap = signatureMap;
    }

    public static <T> T wrap(T object, MyInterceptor interceptor) throws Exception {
        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);

        Class<?> aClass = object.getClass();
        Class<?>[] interfaces = getAllInterfaces(aClass, signatureMap);
        if(interfaces.length > 0){
            //noinspection unchecked
            return (T)Proxy.newProxyInstance(aClass.getClassLoader(),interfaces,new MyPlugin(object,interceptor,signatureMap));
        }
        return object;
    }

    private static Class<?>[] getAllInterfaces(Class<?> aClass, Map<Class<?>, Set<Method>> signatureMap) {
        Set<Class<?>> interfaces = new HashSet<>();
        while (aClass != null){
            for (Class<?> e:
                 aClass.getInterfaces()) {
                if (signatureMap.containsKey(e)) {
                    interfaces.add(e);
                }
            }
            aClass = aClass.getSuperclass();
        }
        return interfaces.toArray(new Class[0]);
    }

    private static Map<Class<?>, Set<Method>> getSignatureMap(MyInterceptor interceptor) throws Exception {
        MyIntercepts annotation = interceptor.getClass().getAnnotation(MyIntercepts.class);
        if(annotation == null){
            throw new Exception("No @Intercepts annotation was found in interceptor " + interceptor.getClass().getName());
        }
        MySignature[] value = annotation.value();
        Map<Class<?>, Set<Method>> map = new HashMap<>();
        for (MySignature signature:
             value) {
            Set<Method> methods = map.computeIfAbsent(signature.type(), k -> new HashSet<>());
            methods.add(signature.type().getMethod(signature.method(),signature.args()));
        }
        return map;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        Set<Method> methods = signatureMap.get(method.getDeclaringClass());
        if(methods != null && methods.contains(method)){
            return interceptor.intercept(new MyInvocation(target,method,args));
        }
        return method.invoke(target,args);
    }


}
