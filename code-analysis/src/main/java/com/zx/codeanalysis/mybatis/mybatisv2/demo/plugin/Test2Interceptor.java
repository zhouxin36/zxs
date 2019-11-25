package com.zx.codeanalysis.mybatis.mybatisv2.demo.plugin;

import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXStatementHandler;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.ZXInterceptor;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.ZXPlugin;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.annotation.ZXIntercepts;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.annotation.ZXSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author zhouxin
 * @date 2018/10/23
 */
@ZXIntercepts({@ZXSignature(clazz = ZXStatementHandler.class, method = "query", args = {String.class, Object[].class, Class.class})})
public class Test2Interceptor implements ZXInterceptor {

  private static final Logger LOGGER = LoggerFactory.getLogger(Test2Interceptor.class);


  @Override
  public Object intercept(Method method, Object[] args, Object target) {
    try {
      LOGGER.info("插件2开始：method:{}", method.toString());
      Object invoke = method.invoke(target, args);
      LOGGER.info("插件2结束");
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
