package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/4/3
 */
public class LC657 {
    public static void main(String[] args) {
        System.out.println(new LC657().judgeCircle("UD"));
        System.out.println(!new LC657().judgeCircle("LL"));
    }

    public boolean judgeCircle(String moves) {
        int horizontal = 0;
        int vertical = 0;
        for (char ch : moves.toCharArray()) {
            switch (ch) {
                case 'U':
                    vertical++;
                    break;
                case 'D':
                    vertical--;
                    break;
                case 'R':
                    horizontal++;
                    break;
                case 'L':
                    horizontal--;
                    break;
                default:
            }
        }
        return vertical == 0 && horizontal == 0;
    }
}
