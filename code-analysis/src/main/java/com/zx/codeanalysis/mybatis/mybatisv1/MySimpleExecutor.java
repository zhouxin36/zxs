package com.zx.codeanalysis.mybatis.mybatisv1;

/**
 * @author zhouxin
 * @date 2018/10/22
 */
public class MySimpleExecutor implements MyExecutor {

    private MyConfiguration configuration;

    public MySimpleExecutor(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T query(String statement, Object[] parameter, MyConfiguration configuration,Class<T> returnType) {
        return configuration.getStatementHandler().query(statement,parameter,configuration.getDateSource(),returnType);
    }
}
