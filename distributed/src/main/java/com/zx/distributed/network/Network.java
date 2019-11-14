package com.zx.distributed.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.NetworkInterface;
import java.net.SocketException;

/**
 * @author zhouxin
 * @date 2019/1/24
 */
public class Network {
    private static final Logger logger = LoggerFactory.getLogger(Network.class);

    public static void main(String[] args) throws Exception {
        NetworkInterface.networkInterfaces().forEach(e -> {
            try {
                logger.info("index:{} | name:{} | displayName:{} | hardwareAddress:{}"
                        , e.getIndex(), e.getName(), e.getDisplayName(), e.getHardwareAddress());
            } catch (SocketException e1) {
                e1.printStackTrace();
            }
        });
    }
}
