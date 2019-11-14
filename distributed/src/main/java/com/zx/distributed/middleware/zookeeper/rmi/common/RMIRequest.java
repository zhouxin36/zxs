package com.zx.distributed.middleware.zookeeper.rmi.common;

import java.io.Serializable;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @author zhouxin
 * @date 2018/12/25
 */
public class RMIRequest implements Serializable {

    private String className;

    private String methodName;

    private Object[] parameters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RMIRequest.class.getSimpleName() + "[", "]")
                .add("className='" + className + "'")
                .add("methodName='" + methodName + "'")
                .add("parameters=" + Arrays.toString(parameters))
                .toString();
    }
}
