package com.zx.distributed.middleware.netty.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhouxin
 * @date 2018-11-02
 */
public class MyWebServer {

  //关闭标志
  static final String CLOSE = "close";
  private static final Logger logger = LoggerFactory.getLogger(MyWebServer.class);
  private static ExecutorService executorService = Executors.newCachedThreadPool();
  //存放所有socket连接
  private static List<Socket> list = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    //新建服务器socket并绑定本地ip,8080端口
    ServerSocket serverSocket = new ServerSocket();
    SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8080);
    serverSocket.bind(socketAddress);
    //
    while (true) {
      logger.info("监听---");
      // 监听连接，此处阻塞，直到收到连接
      Socket accept = serverSocket.accept();
      // 监听到后添加到集合中
      list.add(accept);
      // 输出当前集合大小
      logger.info("监听---成功|list大小:{}" + list.size());
      // 把刚刚的socket丢给一个线程跑，主线程继续监听连接。
      executorService.submit(() -> {
        String str;
        // 从socket中获得输出流inputStream，因为inputStream实现AutoCloseable接口,可定义到try()括号中，使得try块结束后自动调用close关闭socket。(一个好习惯，所有资源都需要关闭)
        try (InputStream inputStream = accept.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             OutputStream outputStream = accept.getOutputStream();
             accept) {
          // 组装字符串
          // 从流中读出的真实字符串
          // 在输出流中读取1024个字节的流数据，并把读的真实长度给len,如果真实长度为-1表示流已关闭，不进行读操作，此处阻塞，知道输出流有数据
          while ((str = reader.readLine()) != null && reader.ready()) {
            // 把读出来的字节数组新建为一个字符串，放入StringBuilder中
            logger.info("----------->{}", str);
          }
          // 组装字符串，使用hashcode表示当前说话对象
          String stringBuilder = "HTTP/1.1\n" +
              "Content-type:application/json\n\n" +
              "content-length:" +
              "返回信息";
          outputStream.write(stringBuilder.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          list.remove(accept);
        }
      });
    }
  }
}
