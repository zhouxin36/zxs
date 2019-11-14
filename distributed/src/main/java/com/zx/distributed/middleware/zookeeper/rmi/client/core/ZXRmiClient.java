package com.zx.distributed.middleware.zookeeper.rmi.client.core;

import com.zx.distributed.middleware.zookeeper.rmi.common.RMIRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author zhouxin
 * @date 2018/12/25
 */
public class ZXRmiClient implements InvocationHandler {

    public static <T> T getInstance(Class<T> clazz){
        //noinspection unchecked
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[]{clazz},new ZXRmiClient());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        RMIRequest rmiRequest = new RMIRequest();
        rmiRequest.setClassName(method.getDeclaringClass().getSimpleName());
        rmiRequest.setMethodName(method.getName());
        rmiRequest.setParameters(args);
        return invokeMethod(rmiRequest, method);
    }

    private Object invokeMethod(RMIRequest rmiRequest, Method method) {
        try (Socket socket = new Socket("127.0.0.1", 8888);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
            objectOutputStream.writeObject(rmiRequest);

            return method.getReturnType().cast(objectInputStream.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}