package com.zx.codeanalysis.mybatis.mybatisv1;

/**
 * @author zhouxin
 * @date 2018/10/22
 */
public interface MyExecutor {
    public <T> T query(String statement, Object[] parameter, MyConfiguration configuration,Class<T> returnType);
}
