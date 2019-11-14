package com.zx.distributed.middleware.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author zhouxin
 * @date 2019/1/2
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SelectorProvider.provider().openSocketChannel();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(8888));
        Selector selector = SelectorProvider.provider().openSelector();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        NIOUtils.handleKeys(selector);
    }

}
