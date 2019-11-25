package com.zx.codeanalysis.pattern.factory.abstr;

import com.zx.codeanalysis.pattern.factory.Mengniu;
import com.zx.codeanalysis.pattern.factory.Milk;
import com.zx.codeanalysis.pattern.factory.Telunsu;

/**
 * @author zhouxin
 * @date 2018/10/16
 */
public class MilkFactory extends AbstractFactory {
  @Override
  public Milk getTelunsu() {
    return new Telunsu();
  }

  @Override
  public Milk getMengniu() {
    return new Mengniu();
  }
}
