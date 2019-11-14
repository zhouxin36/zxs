package com.zx.codeanalysis.mybatis.mybatisv2.config;

import com.zx.codeanalysis.mybatis.mybatisv2.handler.ZXSimpleExecutor;
import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXExecutor;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class ZXSqlSession {

    private ZXConfiguration configuration;

    private ZXExecutor executor;

    public ZXSqlSession(ZXConfiguration configuration) {
        this.configuration = configuration;
        executor = new ZXSimpleExecutor(configuration);
    }
    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz,this);
    }

    public <T> T query(String sql, Object[] args, Class<T> returnType){
        return executor.selectOne(sql,args,returnType);
    }

    public int update(String sql, Object[] args) {
        return executor.update(sql,args);
    }
}
