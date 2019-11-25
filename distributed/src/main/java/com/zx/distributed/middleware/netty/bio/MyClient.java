package com.zx.distributed.middleware.netty.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author zhouxin
 * @date 2018-11-02
 */
public class MyClient {

  private static final Logger logger = LoggerFactory.getLogger(MyClient.class);

  public static void main(String[] args) throws Exception {
    // 新建客户端socket 并连接服务器socket
    Socket socket = new Socket();
    SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8080);
    socket.connect(socketAddress);
    // 定义一个byte数组用于接受字节流
    byte[] rb = new byte[1024];
    // 标志字节流真实长度。 <= 1024
    int len;
    // 从socket中获得输出流输入流，因为输出流输入流实现AutoCloseable接口,可定义到try()括号中，使得try块结束后自动调用close关闭socket。(一个好习惯，所有资源都需要关闭)
    try (InputStream inputStream = socket.getInputStream(); OutputStream outputStream = socket.getOutputStream()) {
      // 定义控制台输入
      Scanner scanner = new Scanner(System.in);
      while (true) {
        // 获得控制台输出结果，此处阻塞，直到控制台回车
        if (scanner.hasNext()) {
          // 获取控制台结果
          String next = scanner.next();
          // 输入流按字节流写入
          outputStream.write(next.getBytes());
          // 如果输入的是结束标志，结束socket连接
          if (next.equals(MyServer.CLOSE)) {
            // 关闭socket连接
            socket.close();
            // 结束死循环
            break;
          }
          // 睡个100毫秒，因为输入输出有延迟，为了下面的输出流能得到上面的输入数据
          Thread.sleep(100);
          StringBuilder stringBuilder = new StringBuilder();
          // 在输出流中读取1024个字节的流数据，并把读的真实长度给len,如果真实长度为-1表示流已关闭，不进行读操作，此处阻塞，知道输出流有数据
          if ((len = inputStream.read(rb)) != -1) {
            // 把读出来的字节数组新建为一个字符串，放入StringBuilder中
            stringBuilder.append(new String(rb, 0, len, StandardCharsets.UTF_8));
          }

          // 输出结果
          logger.info("--->" + stringBuilder.toString());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
