package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/2
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC58 {

    public static void main(String[] args) {
        System.out.println(new LC58().lengthOfLastWord("a") == 1);
        System.out.println(new LC58().lengthOfLastWord("Hello World") == 5);
        System.out.println(new LC58().lengthOfLastWord(" ") == 0);
        System.out.println(new LC58().lengthOfLastWord("     ") == 0);
    }

    public int lengthOfLastWord(String s) {
        return lengthOfLastWord1(s);
    }

    public int lengthOfLastWord1(String s) {
        int start = 0;
        int end = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (end == 0 && s.charAt(i) != ' ') {
                end = i + 1;
            }
            if (end != 0 && s.charAt(i) == ' ') {
                start = i + 1;
                break;
            }
        }
        return end - start;
    }

}
