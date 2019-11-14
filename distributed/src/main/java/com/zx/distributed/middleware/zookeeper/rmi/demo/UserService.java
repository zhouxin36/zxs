package com.zx.distributed.middleware.zookeeper.rmi.demo;

/**
 * @author zhouxin
 * @date 2018/12/26
 */
public class UserService implements IUserInterface {
    @Override
    public String say(String message) {
        return "你说啥？？" + message;
    }
}
