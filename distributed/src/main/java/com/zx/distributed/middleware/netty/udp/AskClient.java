package com.zx.distributed.middleware.netty.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AskClient {
  public static void main(String[] args) {
    //1.确定发送给服务器的信息、服务器地址以及端口
    String mess = "你好，我想咨询个问题";
    byte[] bytes = mess.getBytes();
    InetAddress inetAddress = null;
    try {
      inetAddress = InetAddress.getByName("localhost");
    } catch (Exception e) {
      e.printStackTrace();
    }
    int port = 8800;
    //2.创建数据包，发送指定长度的信息到指定服务器的指定端口
    DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, inetAddress, port);
    //3.创建DatagramSocket对象
    DatagramSocket datagramSocket = null;
    try {
      datagramSocket = new DatagramSocket();
    } catch (Exception e) {
      e.printStackTrace();
    }
    //4.向服务器发送数据包
    try {
      datagramSocket.send(datagramPacket);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //接受服务器的响应并打印
    byte[] bytes1 = new byte[1024];
    //创建接受类型的数据包，数据将存储在数组中
    DatagramPacket datagramPacket1 = new DatagramPacket(bytes1, bytes1.length);
    //通过套接字接受数据
    try {
      datagramSocket.receive(datagramPacket1);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //解析服务器的响应
    String reply = new String(bytes1, 0, datagramPacket1.getLength());
    System.out.println("服务器：" + reply);
    //5.释放资源
    datagramSocket.close();
  }
}
