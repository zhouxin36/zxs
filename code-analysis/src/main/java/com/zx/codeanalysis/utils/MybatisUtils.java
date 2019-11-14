package com.zx.codeanalysis.utils;

public class MybatisUtils {

    public static String columnHandler(String str){
        byte[] chars = str.getBytes();
        byte[] restr = new byte[100];
        int j = 0;
        for(int i = 0; i < chars.length && i < 100; i++){
            if(chars[i] < 96){
                restr[j] = '_';
                restr[j+1] = (byte) (chars[i]+32);
                j = j + 2;
            }else {
                restr[j] = chars[i];
                j++;
            }
        }
        return new String(restr,0,j);
    }

    public static String getFieldByMethod(String mName){
        String substring = mName.substring(3);
        return lowerF(substring);
    }

    public static String lowerF(String name){
        byte[] bytes = name.getBytes();
        bytes[0] += 32;
        return new String(bytes);
    }

}
