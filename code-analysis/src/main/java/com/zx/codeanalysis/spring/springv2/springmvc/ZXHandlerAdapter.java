package com.zx.codeanalysis.spring.springv2.springmvc;

import com.zx.codeanalysis.spring.springv1.springmvcv1.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * @author zhouxin
 * @date 2018/11/14
 */
public class ZXHandlerAdapter {

    private Map<String, Integer> paramsMap;

    public ZXHandlerAdapter(Map<String, Integer> paramsMap) {
        this.paramsMap = paramsMap;
    }

    public ModelAndView handler(HttpServletRequest request, HttpServletResponse response, ZXHandleMapping handlerMapping){

        Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();

        Map<String, String[]> parameterMap = request.getParameterMap();
        Object[] params = new Object[parameterTypes.length];
        for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
            if(!paramsMap.containsKey(stringEntry.getKey())){
                continue;
            }
            Integer integer = paramsMap.get(stringEntry.getKey());
            String s = Arrays.toString(stringEntry.getValue()).trim().replaceAll("[\\[]]", "").replaceAll("\\s", "");

            params[integer]  = caseStringValue(s,parameterTypes[integer]);
        }

        if(paramsMap.containsKey(HttpServletRequest.class.getName())){
            params[paramsMap.get(HttpServletRequest.class.getName())] = request;
        }
        if(paramsMap.containsKey(HttpServletResponse.class.getName())){
            params[paramsMap.get(HttpServletResponse.class.getName())] = response;
        }
        try {
            Object invoke = handlerMapping.getMethod().invoke(handlerMapping.getController(), params);
            if (invoke == null){
                return null;
            }
            boolean equals = handlerMapping.getMethod().getReturnType().equals(ModelAndView.class);
            if(equals){
                return (ModelAndView)invoke;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object caseStringValue(String value,Class<?> clazz){
        if(clazz == String.class){
            return value;
        }else if(clazz == Integer.class){
            return  Integer.valueOf(value);
        }else if(clazz == int.class){
            return Integer.valueOf(value).intValue();
        }else {
            return null;
        }
    }

}
