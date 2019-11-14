package com.zx.codeanalysis.mybatis.mybatisv2.interfaces;

import java.sql.ResultSet;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public interface ZXResultSetHandler {

    <T> T handler(ResultSet resultSet, Class<T> returnType);
}
