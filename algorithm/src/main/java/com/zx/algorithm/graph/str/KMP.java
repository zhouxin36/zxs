package com.zx.algorithm.graph.str;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 子串查找KMP算法
 *
 * @author zhouxin
 * @since 2019/6/4
 */
public class KMP {

    private final static Logger logger = LoggerFactory.getLogger(KMP.class);
    private String patStr;

    private int R = 128;

    private int pathLength;

    private int dfa[][];

    public KMP(String patStr) {
        this.patStr = patStr;
        this.pathLength = patStr.length();
        dfa = new int[R][pathLength];
        dfa[patStr.charAt(0)][0] = 1;
        int X = 0;//重启状态
        for (int j = 1; j < pathLength; j++) {
            for (int k = 0; k < R; k++) {
                dfa[k][j] = dfa[k][X];
            }
            dfa[patStr.charAt(j)][j] = j + 1;
            X = dfa[patStr.charAt(j)][X];
        }
    }

    /**
     * 暴力查询子串
     */
    public int search(String content) {
        int length = content.length();
        int contentCount = 0,patCount = 0;
        for (int i = 0; i < length; i++) {
            contentCount++;
            int j = 0;
            for (int k = i; j < pathLength; j++, k++) {
                patCount++;
                if (content.charAt(k) != patStr.charAt(j)){
                    break;
                }
            }
            if(j == pathLength){
                logger.info("contentCount:{},patCount:{}",contentCount,patCount);
                return i;
            }
        }
        logger.info("contentCount:{},patCount:{}",contentCount,patCount);
        return -1;
    }

    /**
     * KMP子串查询
     */
    public int KMPsearch(String content) {
        int length = content.length();
        int contentCount = 0;
        int i,j;
        for (i = 0,j = 0; i < length && j < pathLength; i++) {
            contentCount++;
            j = dfa[content.charAt(i)][j];
        }
        logger.info("contentCount:{}",contentCount);
        if(j == pathLength){
            return i - pathLength;
        }else {
            return -1;
        }
    }
}
