package com.zx.distributed.middleware.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyClient implements Runnable {


  public static final String str = "00000523<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message><Sys_Head><TRAN_VERSION>10000</TRAN_VERSION><TRAN_CODE>000001</TRAN_CODE><SOURCE_TYPE>VC</SOURCE_TYPE><BRANCH_ID>411459</BRANCH_ID><USER_ID>N14006</USER_ID><TRAN_DATE>20260501</TRAN_DATE><TRAN_TIMESTAMP>201422</TRAN_TIMESTAMP><SERVER_ID>10.64.22.136</SERVER_ID><WS_ID>N4114001</WS_ID><SEQ_NO>${__Random(000000,999999,)}</SEQ_NO></Sys_Head><Body><TERM_MAC>00:18:7D:B5:A2:7D</TERM_MAC><FLAG>SU</FLAG><SOFT_VERSION>20180203</SOFT_VERSION><ARGUMENT_VERSION>20180203</ARGUMENT_VERSION></Body></Message>";

  public static void main(String[] args) {
    for (int i = 0; i < 1; i++) {
      new Thread(new NettyClient(), ">>> this thread " + i).start();
    }
  }

  @Override
  public void run() {
//        EventLoopGroup group = new NioEventLoopGroup();
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(group);
      bootstrap.channel(NioSocketChannel.class)
          .option(ChannelOption.TCP_NODELAY, true)
          .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              ChannelPipeline pipeline = ch.pipeline();
              pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
              pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
              pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
              pipeline.addLast("handler", new MyClient());
              pipeline.addLast("handler2", new TcpServer2Handler());
            }
          });

      for (int i = 0; i < 1; i++) {
        ChannelFuture f = bootstrap.connect("127.0.0.1", 30303).sync();
        f.channel().writeAndFlush(str);
        f.channel().closeFuture().sync();
      }


    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      group.shutdownGracefully();
    }

  }
}
