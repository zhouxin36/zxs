package com.zx.codeanalysis.mybatis.mybatisv2.demo.plugin;

import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXResultSetHandler;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.ZXInterceptor;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.ZXPlugin;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.annotation.ZXIntercepts;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.annotation.ZXSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.ResultSet;

/**
 * @author zhouxin
 * @date 2018/10/23
 */
@ZXIntercepts({@ZXSignature(clazz = ZXResultSetHandler.class, method = "handler", args = {ResultSet.class, Class.class})})
public class Test1Interceptor implements ZXInterceptor {

  private static final Logger LOGGER = LoggerFactory.getLogger(Test1Interceptor.class);


  @Override
  public Object intercept(Method method, Object[] args, Object target) {
    try {
      LOGGER.info("插件1开始：method:{}", method.toString());
      Object invoke = method.invoke(target, args);
      LOGGER.info("插件1结束");
      return invoke;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public <T> T plugin(T target) {
    try {
      return ZXPlugin.wrap(target, this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
