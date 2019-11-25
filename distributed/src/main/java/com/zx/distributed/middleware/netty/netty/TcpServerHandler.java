package com.zx.distributed.middleware.netty.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018-12-31
 */
@ChannelHandler.Sharable
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

  private static final Logger logger = LoggerFactory.getLogger(TcpServerHandler.class);

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
    logger.info("----------->TcpServerHandler#channelActive");
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
    logger.info("----------->TcpServerHandler#channelRead:{}", msg);
//        ctx.pipeline().writeAndFlush("收到pipeline");
//        ctx.writeAndFlush("收到");
//        FullHttpRequest request = new DefaultFullHttpRequest(
//                HttpVersion.HTTP_1_1, HttpMethod.GET, "/", Unpooled.wrappedBuffer("hehe".getBytes()));
//        request.headers()
//                .set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8")
//                .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
//                .set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
    ctx.channel().writeAndFlush(msg);
    ctx.close();
    ReferenceCountUtil.release(msg);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
    logger.info("------->TcpServer2Handler:ex:{}", cause.getMessage());
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    logger.info("----------->TcpServerHandler#channelInactive");
  }
}
