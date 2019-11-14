package com.zx.algorithm.graph.str.version1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @since 2019/6/21
 */
public class BM1 {

    private final static Logger logger = LoggerFactory.getLogger(KMP1.class);

    private String targetStr;

    private int targetLength;

    private char[] chars;

    private int R = 256;

    private int[] select;

    public BM1(String targetStr) {
        this.targetStr = targetStr;
        this.chars = targetStr.toCharArray();
        this.targetLength = targetStr.length();
        select = new int[R];
        for (int i = 0; i < targetLength; i++) {
            select[chars[i]] = i + 1;
        }
    }

    public int BMSearch(String content){
        if (content == null || content.length() < targetLength) {
            return -1;
        }
        int contentCount = 0, patCount = 0;
        int length = content.length();
        char[] contentChars = content.toCharArray();
        int i = 0, j;
        int skip = 0;
        for (;i < length - targetLength; i+= skip){
            contentCount++;
            skip = 0;
            for (j = targetLength - 1; j > 0; j--){
                patCount++;
                if(contentChars[i + j] != chars[j]){
                    skip = j - select[contentChars[i + j]];
                    if(skip < 1){
                        skip = 1;
                    }
                    break;
                }
            }
            if (skip == 0){
                break;
            }
        }
        logger.info("contentCount:{},patCount:{}",contentCount,patCount);
        if(skip == 0){
            return i;
        }else {
            return -1;
        }
    }
}
