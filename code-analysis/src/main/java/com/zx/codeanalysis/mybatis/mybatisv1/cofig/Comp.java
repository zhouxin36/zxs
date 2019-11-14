package com.zx.codeanalysis.mybatis.mybatisv1.cofig;

import java.util.StringJoiner;

/**
 * @author zhouxin
 * @date 2018/10/22
 */
public class Comp {
    private String userId;
    private String company;

    @Override
    public String toString() {
        return new StringJoiner(", ", Comp.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("company='" + company + "'")
                .toString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
