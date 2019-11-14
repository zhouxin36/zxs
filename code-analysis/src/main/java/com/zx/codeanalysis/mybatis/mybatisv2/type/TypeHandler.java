package com.zx.codeanalysis.mybatis.mybatisv2.type;

import java.sql.ResultSet;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public interface TypeHandler<T> {

    T getValue(ResultSet resultSet,String name) throws Exception;
}
