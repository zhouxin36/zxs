package com.zx.codeanalysis.mybatis.mybatisv1.plugin;

import java.lang.reflect.Method;

/**
 * @author zhouxin
 * @date 2018/10/23
 */
public class MyInvocation {

    private Object target;
    private Method method;
    private Object[] args;

    public MyInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }

    public Object proceed()throws Throwable{
        return method.invoke(target,args);
    }
}
