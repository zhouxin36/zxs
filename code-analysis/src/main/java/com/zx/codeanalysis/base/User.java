package com.zx.codeanalysis.base;

import java.util.StringJoiner;

/**
 * @author zhouxin
 * @date 2018/10/22
 */
public class User {
    private String id;
    private String userName;
    private Integer age;
    private String telNo;
    private Integer sex;
    private String birthday;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("userName='" + userName + "'")
                .add("age=" + age)
                .add("telNo='" + telNo + "'")
                .add("sex=" + sex)
                .add("birthday='" + birthday + "'")
                .toString();
    }
}
