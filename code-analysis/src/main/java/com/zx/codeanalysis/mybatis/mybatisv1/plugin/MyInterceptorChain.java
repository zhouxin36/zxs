package com.zx.codeanalysis.mybatis.mybatisv1.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhouxin
 * @date 2018/10/23
 */
public class MyInterceptorChain {

  private final List<MyInterceptor> MyInterceptors = new ArrayList<>();

  public <T> T pluginAll(T target) {
    for (MyInterceptor MyInterceptor : MyInterceptors) {
      target = MyInterceptor.plugin(target);
    }
    return target;
  }

  public void addMyInterceptor(MyInterceptor MyInterceptor) {
    MyInterceptors.add(MyInterceptor);
  }

  public List<MyInterceptor> getMyInterceptors() {
    return Collections.unmodifiableList(MyInterceptors);
  }

}
