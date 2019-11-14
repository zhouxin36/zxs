package com.zx.codeanalysis.pattern.proxy.proxy;

/**
 * @author zhouxin
 * @date 2019/1/3
 */
public class GetBooleanAction implements java.security.PrivilegedAction<Boolean> {
    private String theProp;

    /**
     * Constructor that takes the name of the system property whose boolean
     * value needs to be determined.
     *
     * @param theProp the name of the system property.
     */
    public GetBooleanAction(String theProp) {
        this.theProp = theProp;
    }

    /**
     * Determines the boolean value of the system property whose name was
     * specified in the constructor.
     *
     * @return the <code>Boolean</code> value of the system property.
     */
    public Boolean run() {
        return Boolean.getBoolean(theProp);
    }
}
