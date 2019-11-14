package com.zx.algorithm.graph.str.version1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @since 2019/6/20
 */
public class KMP1 {

    private final static Logger logger = LoggerFactory.getLogger(KMP1.class);

    private String targetStr;

    private int targetLength;

    private char[] chars;

    private int R = 256;

    private int[][] dfa;

    public KMP1(String targetStr) {
        this.targetStr = targetStr;
        this.chars = targetStr.toCharArray();
        this.targetLength = targetStr.length();
        dfa = new int[R][targetLength];
        dfa[chars[0]][0] = 1;
        int X = 0;//重启状态
        for (int i = 1; i < targetLength; i++) {
            for (int a = 0; a < R; a++) {
                dfa[a][i] = dfa[a][X];
            }
            dfa[chars[i]][i] = i + 1;
            X = dfa[chars[i]][X];
        }
    }

    public int search(String pathStr) {
        if (pathStr == null || pathStr.length() < targetLength) {
            return -1;
        }
        int contentCount = 0, patCount = 0;
        char[] childChars = pathStr.toCharArray();
        int i, j;
        for (i = 0; i < pathStr.length(); i++) {
            contentCount++;
            for (j = 0; j < targetLength; j++) {
                patCount++;
                if (this.chars[j] != childChars[i + j]) {
                    break;
                }
            }
            if (j == targetLength) {
                break;
            }
        }
        logger.info("contentCount:{},patCount:{}", contentCount, patCount);
        if (i == pathStr.length()) {
            return -1;
        } else {
            return i;
        }
    }

    public int KMPSearch(String pathStr) {
        if (pathStr == null || pathStr.length() < targetLength) {
            return -1;
        }
        int contentCount = 0;
        char[] childChars = pathStr.toCharArray();
        int i, j = 0;
        for (i = 0; i < pathStr.length() && j < targetLength; i++) {
            contentCount++;
            j = dfa[childChars[i]][j];
        }
        logger.info("contentCount:{}", contentCount);
        if(j == targetLength){
            return i - targetLength;
        }else {
            return -1;
        }
    }
}
