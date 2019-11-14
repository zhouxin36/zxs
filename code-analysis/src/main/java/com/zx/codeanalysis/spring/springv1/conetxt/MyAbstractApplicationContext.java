package com.zx.codeanalysis.spring.springv1.conetxt;

/**
 * @author zhouxin
 * @date 2018/10/30
 */
public abstract class MyAbstractApplicationContext {

    protected void onRefresh(){

    }

    protected abstract void refreshBeanFactory();
}
