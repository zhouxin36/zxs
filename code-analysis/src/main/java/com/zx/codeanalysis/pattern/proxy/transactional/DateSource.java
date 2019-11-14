package com.zx.codeanalysis.pattern.proxy.transactional;

/**
 * @author zhouxin
 * @date 2018/10/22
 */
public enum DateSource {
    COMMON("com.mysql.jdbc.Driver"
            ,"jdbc:mysql://zhouxin.chhkfjisj1vc.us-east-2.rds.amazonaws.com:3305/zhouxin?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false"
            ,"zhouxin"
            ,"zhouxin36"),
    ;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    DateSource(String driverClass, String url, String userName, String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
