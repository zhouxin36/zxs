package com.zx.codeanalysis.mybatis.mybatisv2.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class  ZXInterceptorChain{

    private List<ZXInterceptor> interceptorList = new ArrayList<>();

    public <T> T pluginAll(T target){
        for (ZXInterceptor interceptor:
             interceptorList) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(ZXInterceptor interceptor){
        interceptorList.add(interceptor);
    }
}
