package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/21
 */
public class LC422 {

    public static void main(String[] args) {
        System.out.println(new LC422().validWordSquare(new String[]{
                "abcd",
                "bnrt",
                "crmy",
                "dtye"
        }));
        System.out.println(new LC422().validWordSquare(new String[]{
                "abcd",
                "bnrt",
                "crm",
                "dt"
        }));
        System.out.println(!new LC422().validWordSquare(new String[]{
                "ball",
                "area",
                "read",
                "lady"
        }));
    }

    boolean validWordSquare(String[] words) {
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (j < words.length && i < words[j].length() && words[i].charAt(j) == words[j].charAt(i)){

                }else {
                    return false;
                }
            }
        }
        return true;
    }
}
