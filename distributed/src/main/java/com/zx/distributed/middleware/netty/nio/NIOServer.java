package com.zx.distributed.middleware.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author zhouxin
 * @date 2019/1/2
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        /**
         * @see sun.nio.ch.SelectorProviderImpl
         */
        ServerSocketChannel serverSocketChannel = SelectorProvider.provider().openServerSocketChannel();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        Selector selector = SelectorProvider.provider().openSelector();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        NIOUtils.handleKeys(selector);
    }


}
