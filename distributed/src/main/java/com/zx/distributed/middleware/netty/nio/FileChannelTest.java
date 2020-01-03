package com.zx.distributed.middleware.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author zhouxin
 * @since 2020/1/1
 */
public class FileChannelTest {

    public static void main(String[] args) throws Exception{
        from();
    }

    private static void from() throws Exception{
        FileInputStream a = new FileInputStream("F:/new3.txt");
        FileOutputStream b = new FileOutputStream("F:/aaa.txt");
        FileChannel aChannel = a.getChannel();
        FileChannel bChannel = b.getChannel();
        long size = aChannel.size();
        bChannel.position(bChannel.size());
        long position = 0;
        while (position < size) {
            // transferFrom transferTo 功能一样，只是调用顺序不一样
            position += bChannel.transferFrom(aChannel, position, 100);
            System.out.println("current position " + position);
        }
    }
}
