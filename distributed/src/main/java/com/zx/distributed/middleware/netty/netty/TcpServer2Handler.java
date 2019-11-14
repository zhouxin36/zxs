package com.zx.distributed.middleware.netty.netty;

import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018-12-31
 */
@ChannelHandler.Sharable
public class TcpServer2Handler extends ChannelOutboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TcpServer2Handler.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//        super.write(ctx, msg, promise);
        if (promise.isSuccess()) {
            logger.info("-------------->TcpServer2Handler:write:{}",msg);
        }else {
            logger.info("-------------->TcpServer2Handler:write-false:{}", msg);
        }
        ctx.write(msg,promise);
        promise.addListener(future->{
            if (future.isSuccess()) {
                logger.info("-------------->TcpServer2Handler:future:");
            }else {
                logger.info("-------------->TcpServer2Handler:future-false:{}", future.cause().getMessage());
            }
        });
//        ctx.close();
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        logger.info("------->TcpServer2Handler:ex:{}",cause.getMessage());
    }
}
