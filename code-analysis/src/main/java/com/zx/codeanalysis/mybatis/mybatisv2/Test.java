package com.zx.codeanalysis.mybatis.mybatisv2;

import com.zx.codeanalysis.mybatis.mybatisv2.config.ZXConfiguration;
import com.zx.codeanalysis.mybatis.mybatisv2.config.ZXSqlSession;
import com.zx.codeanalysis.mybatis.mybatisv2.demo.Demo;
import com.zx.codeanalysis.mybatis.mybatisv2.demo.plugin.Test1Interceptor;
import com.zx.codeanalysis.mybatis.mybatisv2.demo.plugin.Test2Interceptor;
import com.zx.codeanalysis.mybatis.mybatisv2.demo.plugin.Test3Interceptor;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.ZXInterceptorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        ZXInterceptorChain zxInterceptorChain = new ZXInterceptorChain();
        zxInterceptorChain.addInterceptor(new Test1Interceptor());
        zxInterceptorChain.addInterceptor(new Test2Interceptor());
        zxInterceptorChain.addInterceptor(new Test3Interceptor());
        ZXConfiguration configuration = new ZXConfiguration(zxInterceptorChain);
        ZXSqlSession sqlSession = new ZXSqlSession(configuration);
        Demo mapper = sqlSession.getMapper(Demo.class);
        logger.info(mapper.select("dc1dc52e-79b9-11e8-9c15-fdc7eb479f4a").toString());
        logger.info(mapper.selectTById("65465345345").toString());
        logger.info(mapper.count().toString());
    }
}
