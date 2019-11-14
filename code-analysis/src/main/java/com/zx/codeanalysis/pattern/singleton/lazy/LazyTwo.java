package com.zx.codeanalysis.pattern.singleton.lazy;

/**
 * @author zhouxin
 * @date 2018/10/16
 */
public class LazyTwo {

    private static boolean initialized = false;

    private LazyTwo(){
        synchronized (LazyTwo.class){
            if(!initialized){
                initialized = !initialized;
            }else {
                throw new RuntimeException("单例被侵犯");
            }
        }
    }

    private static final LazyTwo getInstance(){return LazyHolder.LAZY;}

    private static class LazyHolder{
        private static final LazyTwo LAZY = new LazyTwo();
    }
}
