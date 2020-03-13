package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/13
 */
public class LC243 {

    public static void main(String[] args) {
        System.out.println(new LC243().shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice") == 3);
        System.out.println(new LC243().shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding") == 1);
    }

    public int shortestDistance(String[] words, String word1, String word2) {
        int pos1 = -1;
        int pos2 = -1;
        int distance = words.length - 1;
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) {
                pos1 = i;
                if (pos2 != -1 && pos1 - pos2 < distance) distance = pos1 - pos2;
            } else if (word2.equals(words[i])) {
                pos2 = i;
                if (pos1 != -1 && pos2 - pos1 < distance) distance = pos2 - pos1;
            }
        }
        return distance;
    }
}
