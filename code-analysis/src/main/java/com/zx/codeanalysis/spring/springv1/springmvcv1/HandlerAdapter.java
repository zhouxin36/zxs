package com.zx.codeanalysis.spring.springv1.springmvcv1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * @author zhouxin
 * @date 2018-10-28
 */
public class HandlerAdapter {

    private Map<String,Integer> paramMapping;

    public HandlerAdapter(Map<String,Integer> paramMapping){
        this.paramMapping = paramMapping;
    }

    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handlerMapping) throws Exception{
        Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();

        Map<String, String[]> parameterMap = req.getParameterMap();

         Object[] paramValues = new Object[parameterTypes.length];

        for (Map.Entry<String, String[]> map:
             parameterMap.entrySet()) {
            if(!this.paramMapping.containsKey(map.getKey())){
                continue;
            }
            int index = this.paramMapping.get(map.getKey());
            String value = Arrays.toString(map.getValue()).replaceAll("[\\[]]","").replaceAll("\\s","");

            //因为页面上传过来的值都是String类型的，而在方法中定义的类型是千变万化的
            //要针对我们传过来的参数进行类型转换
            paramValues[index] = caseStringValue(value,parameterTypes[index]);

        }

        if(this.paramMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = this.paramMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }

        if(this.paramMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = this.paramMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        //4、从handler中取出controller、method，然后利用反射机制进行调用

        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(),paramValues);

        if(result == null){ return  null; }

        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == ModelAndView.class;
        if(isModelAndView){
            return (ModelAndView)result;
        }else{
            return null;
        }
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
