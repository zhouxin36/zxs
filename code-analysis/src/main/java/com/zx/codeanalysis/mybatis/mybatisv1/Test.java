package com.zx.codeanalysis.mybatis.mybatisv1;

import com.zx.codeanalysis.mybatis.mybatisv1.cofig.Dao;
import com.zx.codeanalysis.base.DateSource;
import com.zx.codeanalysis.mybatis.mybatisv1.plugin.MyInterceptorChain;
import com.zx.codeanalysis.mybatis.mybatisv1.plugin.test.Test1Interceptor;
import com.zx.codeanalysis.mybatis.mybatisv1.plugin.test.Test2Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zhouxin
 * @date 2018/10/22
 */
public class Test {

    private final static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        MyInterceptorChain myInterceptorChain = new MyInterceptorChain();
        myInterceptorChain.addMyInterceptor(new Test1Interceptor());
        myInterceptorChain.addMyInterceptor(new Test2Interceptor());
        MyConfiguration configuration = new MyConfiguration(DateSource.COMMON, myInterceptorChain);
        MySqlSession sqlSession = new MySqlSession(configuration, new MySimpleExecutor(configuration));
        Dao mapper = sqlSession.getMapper(Dao.class);
        logger.info(mapper.selectById("dc1dc52e-79b9-11e8-9c15-fdc7eb479f4a").toString());
        logger.info(mapper.selectTById("65465345345").toString());
        logger.info(mapper.count().toString());
    }
}
