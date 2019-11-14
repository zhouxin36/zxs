package com.zx.distributed.middleware.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

/**
 * @author zhouxin
 * @date 2018-12-31
 */
public class NettyServer {
    private static final String IP = "127.0.0.1";
    private static final int port = 6666;
    private static final int BIZ_GROUP_SIZE =  Runtime.getRuntime().availableProcessors() * 2;

    private static final int BIZ_THREAD_SIZE = 100;

    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZ_GROUP_SIZE);
    private static final EventLoopGroup workGroup = new NioEventLoopGroup(BIZ_THREAD_SIZE);

    public static void start() throws Exception {


        ServerBootstrap serverBootstrap = initServerBootstrap();


        ChannelFuture channelFuture = serverBootstrap.bind(IP, port).sync();

        channelFuture.channel().closeFuture().sync();
        System.out.println("server start");

    }

    private static ServerBootstrap initServerBootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
//                        pipeline.addLast("decoder", new HttpRequestDecoder());
//                        pipeline.addLast("deflater", new HttpContentCompressor());
//                        pipeline.addLast("encoder", new HttpResponseEncoder());
//                        pipeline.addLast("chunked", new ChunkedWriteHandler());
                        pipeline.addLast(new TcpServerHandler());
//                        pipeline.addLast(new TcpServer2Handler());
                    }
                });
        return serverBootstrap;
    }

    protected static void shutdown(){
        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("启动Server...");
        NettyServer.start();
    }

}
