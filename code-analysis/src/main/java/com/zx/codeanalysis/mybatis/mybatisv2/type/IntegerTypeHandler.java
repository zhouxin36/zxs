package com.zx.codeanalysis.mybatis.mybatisv2.type;

import java.sql.ResultSet;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class IntegerTypeHandler implements TypeHandler<Integer> {
  @Override
  public Integer getValue(ResultSet resultSet, String name) throws Exception {
    return resultSet.getInt(name);
  }
}
