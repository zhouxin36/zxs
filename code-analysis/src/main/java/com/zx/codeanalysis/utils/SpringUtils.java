package com.zx.codeanalysis.utils;

/**
 * @author zhouxin
 * @date 2018/10/26
 */
public class SpringUtils {

    public static String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
