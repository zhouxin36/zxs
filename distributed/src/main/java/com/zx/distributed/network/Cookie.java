package com.zx.distributed.network;

/**
 * @author zhouxin
 * @date 2019/1/24
 */
public class Cookie {

    public static void main(String[] args) {
        java.net.CookieManager cm = new java.net.CookieManager();
        java.net.CookieHandler.setDefault(cm);
    }
}
