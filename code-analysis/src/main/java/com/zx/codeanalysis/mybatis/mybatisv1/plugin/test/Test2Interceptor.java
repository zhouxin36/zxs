package com.zx.codeanalysis.mybatis.mybatisv1.plugin.test;

import com.zx.codeanalysis.mybatis.mybatisv1.StatementHandler;
import com.zx.codeanalysis.base.DateSource;
import com.zx.codeanalysis.mybatis.mybatisv1.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author zhouxin
 * @date 2018/10/23
 */
@MyIntercepts({@MySignature(type = StatementHandler.class, method = "query", args = {String.class, Object[].class, DateSource.class,Class.class})})
public class Test2Interceptor implements MyInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(Test2Interceptor.class);

    @Override
    public Object intercept(MyInvocation invocation) throws Throwable {
        logger.info("test2 before");
        Object proceed = invocation.proceed();
        logger.info("test2 after");
        return proceed;
    }

    @Override
    public <T> T plugin(T target) {
        try {
            return MyPlugin.wrap(target, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
