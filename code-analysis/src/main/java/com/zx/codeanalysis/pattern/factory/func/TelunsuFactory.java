package com.zx.codeanalysis.pattern.factory.func;

import com.zx.codeanalysis.pattern.factory.Milk;
import com.zx.codeanalysis.pattern.factory.Telunsu;

/**
 * @author zhouxin
 * @date 2018/10/16
 */
public class TelunsuFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new Telunsu();
    }
}
