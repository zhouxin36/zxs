package com.zx.jdkanalysis.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * @author zhouxin
 * @date 2019/1/18
 */
public class User implements Serializable {

    private final static Logger logger = LoggerFactory.getLogger(User.class);

    private String id;

    private String userName;

    public User(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User() {
        this.id = UUID.randomUUID().toString();
        this.userName = "zhouxin";
        logger.info("user------------------{}",toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("userName='" + userName + "'")
                .toString();
    }
}
