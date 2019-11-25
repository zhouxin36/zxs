package com.zx.codeanalysis.mybatis.mybatisv2.plugin;

import java.lang.reflect.Method;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public interface ZXInterceptor {

  Object intercept(Method method, Object[] args, Object target);

  <T> T plugin(T target);
}
