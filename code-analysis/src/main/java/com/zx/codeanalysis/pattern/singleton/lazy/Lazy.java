package com.zx.codeanalysis.pattern.singleton.lazy;

/**
 * @author zhouxin
 * @date 2018/10/16
 */
public class Lazy {

    private static Lazy lazy = null;

    private Lazy(){}

    public static Lazy getInstance(){
        if(lazy == null){
            lazy = new Lazy();
        }
        return lazy;
    }
}
