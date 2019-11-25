package com.zx.distributed.middleware.netty.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

public class AskServer {
  public static void main(String[] args) {
    try {
      //1.创建接受方(服务器)套接字，并绑定端口号
      DatagramSocket datagramSocket = new DatagramSocket(8800);
      //2.确定数据包接受的数据的数组大小
      byte[] bytes = new byte[1024];
      //3.创建接受类型的数据包，数据将存储在数组中
      DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
      //4.通过套接字接受数据
      datagramSocket.receive(datagramPacket);
      //5.解析发送方发送的数据
      String mess = new String(bytes, 0, datagramPacket.getLength());
      System.out.println("客户端：" + mess);
      //响应客户端
      String reply = "你好，请咨询";
      byte[] replys = reply.getBytes();
      //响应地址
      SocketAddress socketAddress = datagramPacket.getSocketAddress();
      //数据包
      DatagramPacket datagramPacket1 = new DatagramPacket(replys, replys.length, socketAddress);
      //发送
      datagramSocket.send(datagramPacket1);
      //6.释放资源
      datagramSocket.close();
    } catch (SocketException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
