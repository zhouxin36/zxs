package com.zx.codeanalysis.pattern.factory.simple;

import com.zx.codeanalysis.pattern.factory.Mengniu;
import com.zx.codeanalysis.pattern.factory.Milk;
import com.zx.codeanalysis.pattern.factory.Telunsu;

/**
 * @author zhouxin
 * @date 2018/10/16
 */
public class SimpleFactory {

  public Milk getMilk(String name) {
    if ("特仑苏".equals(name)) {
      return new Telunsu();
    } else if ("蒙牛".equals(name)) {
      return new Mengniu();
    } else {
      return null;
    }
  }
}
