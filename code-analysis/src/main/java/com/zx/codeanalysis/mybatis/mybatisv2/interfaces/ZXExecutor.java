package com.zx.codeanalysis.mybatis.mybatisv2.interfaces;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public interface ZXExecutor {

    <T> T selectOne(String sql, Object[] args, Class<T> returnType);

    int update(String sql, Object[] args);
}
