package com.zx.distributed.middleware.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @since 2019/3/19
 */
public class Test1 {

  private static final Logger logger = LoggerFactory.getLogger(Test1.class);

  public static void main(String[] args) {
    UnpooledByteBufAllocator unpooledByteBufAllocator = new UnpooledByteBufAllocator(true);
    ByteBuf heapBuf = unpooledByteBufAllocator.heapBuffer();
    heapBuf.writeChar(86);
    heapBuf.writeChar(86);
    if (heapBuf.hasArray()) {
      byte[] array = heapBuf.array();
      int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
      int length = heapBuf.readableBytes();
      handleArray(array, offset, length);
    }
    ByteBuf directBuf = unpooledByteBufAllocator.directBuffer();
    directBuf.writeChar(86);
    if (!directBuf.hasArray()) {
      int length = directBuf.readableBytes();
      byte[] array = new byte[length];
      directBuf.getBytes(directBuf.readerIndex(), array);
      handleArray(array, 0, length);
    }

  }

  private static void handleArray(byte[] array, int offset, int length) {
    logger.info("String:{},offset:{},length:{}", new String(array), offset, length);
  }
}
