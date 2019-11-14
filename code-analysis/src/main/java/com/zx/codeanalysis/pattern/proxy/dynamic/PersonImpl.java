package com.zx.codeanalysis.pattern.proxy.dynamic;

/**
 * @author zhouxin
 * @since 2019/2/20
 */
public class PersonImpl implements Person {
    @Override
    public User giveMoney() {
        return new User();
    }
}
