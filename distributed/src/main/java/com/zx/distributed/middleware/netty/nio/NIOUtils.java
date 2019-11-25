package com.zx.distributed.middleware.netty.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Set;

/**
 * @author zhouxin
 * @date 2019/1/2
 */
public class NIOUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(NIOUtils.class);

  private static final int BUFF = 256;

  private static final Charset UTF_8 = StandardCharsets.UTF_8;

  private static final Scanner IN = new Scanner(System.in);

  public static void handleKeys(Selector selector) throws IOException {
    while (true) {
      int select = selector.select(1000 * 10);
      if (select == 0) {
        continue;
      }
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      for (SelectionKey selectionKey :
          selectionKeys) {
        selectionKeys.remove(selectionKey);
        if (selectionKey.isValid()) {
          try {
            handleKey(selector, selectionKey);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  private static void handleKey(Selector selector, SelectionKey selectionKey) throws IOException {
    // 接受连接就绪
    if (selectionKey.isAcceptable()) {
      LOGGER.info("------------->accept");
      handleAcceptableKey(selector, selectionKey);
    }
    // 连接
    if (selectionKey.isConnectable()) {
      LOGGER.info("------------->connect");
      handleConnectKey(selector, selectionKey);
    }
    // 读就绪
    if (selectionKey.isReadable()) {
      LOGGER.info("------------->read");
//            handleReadableKey(selectionKey);
      handleReadableKeyV2(selector, selectionKey);
    }
    // 写就绪
    if (selectionKey.isWritable()) {
      LOGGER.info("------------->write");
//            handleWritableKey(selectionKey);
      handleWritableKeyV2(selector, selectionKey);
    }
  }

  private static void handleWritableKey(SelectionKey selectionKey) {
    // 获取本地的socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, ByteBuffer.allocate(BUFF));
    //ByteBuffer.allocate(BUFF) 的数据，用于本地传输
    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
    if (IN.hasNext()) {
      String s = IN.next();
      byteBuffer.put(s.getBytes());
      byteBuffer.flip();
      selectionKey.interestOps(SelectionKey.OP_READ);
    }
  }

  private static void handleWritableKeyV2(Selector selector, SelectionKey selectionKey) throws IOException {
    SelectableChannel channel = selectionKey.channel();
    if (IN.hasNext()) {
      String s = IN.next();
      if (channel instanceof SocketChannel) {
        SocketChannel socketChannel = (SocketChannel) channel;
        socketChannel.write(ByteBuffer.allocate(BUFF).put(s.getBytes()).flip());
        socketChannel.register(selector, SelectionKey.OP_READ);
      } else {
        LOGGER.error("------------------->selectionKey.channel() 转 SocketChannel 异常");
      }
    }
  }

  private static void handleReadableKey(SelectionKey selectionKey) {
    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
    CharBuffer charBuffer = UTF_8.decode(byteBuffer);
    byteBuffer.flip();
//        byteBuffer.rewind();
    byteBuffer.clear();
    selectionKey.interestOpsOr(SelectionKey.OP_WRITE);
    LOGGER.info("--------->{}说：{}", selectionKey.channel(), charBuffer.toString());
  }

  private static void handleReadableKeyV2(Selector selector, SelectionKey selectionKey) throws IOException {
    SelectableChannel channel = selectionKey.channel();
    if (channel instanceof SocketChannel) {
      SocketChannel socketChannel = (SocketChannel) channel;
      ByteBuffer byteBuffer = ByteBuffer.allocate(BUFF);
      int read = socketChannel.read(byteBuffer);
      if (read != -1) {
        LOGGER.info("--------->{}说：{}", selectionKey.channel(), UTF_8.decode(byteBuffer.flip()).toString());
      }
      socketChannel.register(selector, SelectionKey.OP_WRITE);
    } else {
      LOGGER.error("------------------->selectionKey.channel() 转 SocketChannel 异常");
    }
  }

  private static void handleConnectKey(Selector selector, SelectionKey selectionKey) throws IOException {
    SelectableChannel channel = selectionKey.channel();
    if (channel instanceof SocketChannel) {
      SocketChannel socketChannel = (SocketChannel) channel;
      if (!(socketChannel.isConnectionPending() && socketChannel.finishConnect())) {
        return;
      }
      socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
    } else {
      LOGGER.error("------------------->selectionKey.channel() 转 SocketChannel 异常");
    }
  }

  private static void handleAcceptableKey(Selector selector, SelectionKey selectionKey) throws IOException {
    SelectableChannel channel = selectionKey.channel();
    if (channel instanceof ServerSocketChannel) {
      SocketChannel socketChannel = ((ServerSocketChannel) channel).accept();
      socketChannel.configureBlocking(false);
      socketChannel.register(selector, SelectionKey.OP_WRITE);
    } else {
      LOGGER.error("------------------->selectionKey.channel() 转 ServerSocketChannel 异常");
    }
  }
}
