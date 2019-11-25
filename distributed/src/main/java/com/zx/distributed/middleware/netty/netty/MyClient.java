package com.zx.distributed.middleware.netty.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class MyClient extends ChannelInboundHandlerAdapter {

  private static final Logger logger = LoggerFactory.getLogger(TcpServerHandler.class);

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    logger.info("client receieve message: {}", msg);
    ctx.close();
    ReferenceCountUtil.release(msg);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    logger.info("get client exception :{}", cause.getMessage());
  }
}
