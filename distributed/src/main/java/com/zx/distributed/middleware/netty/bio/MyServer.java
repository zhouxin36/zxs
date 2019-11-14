package com.zx.distributed.middleware.netty.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
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
public class MyServer {

    private final static Logger logger = LoggerFactory.getLogger(MyServer.class);

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    //关闭标志
    static final String CLOSE = "close";

    //存放所有socket连接
    private static List<Socket> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        //新建服务器socket并绑定本地ip,8080端口
        ServerSocket serverSocket = new ServerSocket();
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6666);
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
                // 定义一个byte数组用于接受字节流
                byte[] rb = new byte[1024];
                // 标志字节流真实长度。 <= 1024
                int len;
                // 从socket中获得输出流inputStream，因为inputStream实现AutoCloseable接口,可定义到try()括号中，使得try块结束后自动调用close关闭socket。(一个好习惯，所有资源都需要关闭)
                try (InputStream inputStream = accept.getInputStream()) {
                    while (true) {
                        // 组装字符串
                        StringBuilder stringBuilder = new StringBuilder();
                        // 从流中读出的真实字符串
                        StringBuilder strReal = new StringBuilder();
                        // 在输出流中读取1024个字节的流数据，并把读的真实长度给len,如果真实长度为-1表示流已关闭，不进行读操作，此处阻塞，知道输出流有数据
                        if ((len = inputStream.read(rb)) != -1) {
                            // 把读出来的字节数组新建为一个字符串，放入StringBuilder中
                            strReal.append(new String(rb, 0, len, StandardCharsets.UTF_8));
                        }
                        // 如果读出的是结束标志，执行结束操作
                        if(strReal.toString().equals(CLOSE)){
                            // 关闭当前socekt
                            accept.close();
                            // 从list中移除当前socket
                            list.remove(accept);
                            // 结束当前死循环，此连接已结束
                            break;
                        }
                        // 组装字符串，使用hashcode表示当前说话对象
                        stringBuilder.append(accept.hashCode()).append(" 说：");
                        stringBuilder.append(strReal.toString());
                        stringBuilder.append('\n');
                        // 遍历socket集合，并给每个socket发送组装的字符串，实现群发功能
                        list.forEach(o -> {
                            try {
                                //当前流不能结束，所以没写在try()中
                                o.getOutputStream().write(stringBuilder.toString().getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
