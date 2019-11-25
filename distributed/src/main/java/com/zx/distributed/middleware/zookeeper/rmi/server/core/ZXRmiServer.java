package com.zx.distributed.middleware.zookeeper.rmi.server.core;

import com.zx.distributed.middleware.zookeeper.rmi.common.RMIRequest;
import com.zx.distributed.middleware.zookeeper.rmi.demo.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhouxin
 * @date 2018/12/25
 */
public class ZXRmiServer {

  private static final ExecutorService executorService = Executors.newCachedThreadPool();

  private static final Logger logger = LoggerFactory.getLogger(ZXRmiServer.class);

  private static Map<String, Class<?>> handlerMap;

  static {
    handlerMap = new HashMap<>();
    handlerMap.put(UserService.class.getInterfaces()[0].getSimpleName(), UserService.class);
  }

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket();
    SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8888);
    serverSocket.bind(socketAddress);
    while (true) {
      Socket accept = serverSocket.accept();
      executorService.submit(() -> {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream())) {
          RMIRequest rmiRequest = (RMIRequest) objectInputStream.readObject();
          logger.info("----------->{}", rmiRequest);
          Class<?> aClass = handlerMap.get(rmiRequest.getClassName());
          Class<?>[] classes = new Class[rmiRequest.getParameters().length];
          for (int i = 0; i < rmiRequest.getParameters().length; i++) {
            classes[i] = rmiRequest.getParameters()[i].getClass();
          }
          if (aClass != null) {
            Method method = aClass.getMethod(rmiRequest.getMethodName(), classes);
            Object invoke = method.invoke(aClass.getDeclaredConstructor().newInstance(), rmiRequest.getParameters());
            objectOutputStream.writeObject(invoke);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    }
  }
}
