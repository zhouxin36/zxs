package com.zx.codeanalysis.mybatis.mybatisv1;

import java.sql.ResultSet;

/**
 * @author zhouxin
 * @date 2018/10/23
 */
public interface ResultSetHandler {
    public <T> T handler(ResultSet resultSet, Class<T> returnType);
}
