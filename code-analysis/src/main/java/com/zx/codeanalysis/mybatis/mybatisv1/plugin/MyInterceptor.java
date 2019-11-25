package com.zx.codeanalysis.mybatis.mybatisv1.plugin;

import java.util.Properties;

/**
 * @author zhouxin
 * @date 2018/10/23
 */
public interface MyInterceptor {

  Object intercept(MyInvocation invocation) throws Throwable;

  <T> T plugin(T target);

  void setProperties(Properties properties);
}
