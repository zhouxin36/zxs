package com.zx.algorithm.graph.str;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @since 2019/6/4
 */
public class BM {

    private final static Logger logger = LoggerFactory.getLogger(BM.class);

    private String patStr;

    private int R = 128;

    private int pathLength;

    private int[] right;

    public BM(String patStr) {
        this.patStr = patStr;
        this.pathLength = patStr.length();
        this.right = new int[R];
        for (int i = 0; i < R; i++) {
            right[i] = -1;
        }
        for (int i = 0; i < pathLength; i++) {
            right[patStr.charAt(i)] = i;
        }
    }

    /**
     * BM子串查询
     */
    public int BMsearch(String content) {
        int length = content.length();
        int contentCount = 0,patCount = 0;
        int i,j,skip;
        for (i = 0; i < length - pathLength; i+= skip){
            contentCount++;
            skip = 0;
            for (j = pathLength - 1; j >= 0; j--) {
                patCount++;
                if (content.charAt(i + j) != patStr.charAt(j)) {
                    skip = j - right[content.charAt(i + j)];
                    if(skip < 1) skip = 1;
                    break;
                }
            }
            if(skip == 0){
                logger.info("contentCount:{},patCount:{}",contentCount,patCount);
                return i;
            }
        }
        logger.info("contentCount:{},patCount:{}",contentCount,patCount);
        return -1;
    }
}
