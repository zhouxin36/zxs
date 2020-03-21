package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/21
 */
public class LC443 {

    public static void main(String[] args) {
        System.out.println(new LC443().compress(new char[]{'a','a','b','b','c','c','c'}) == 6);
        System.out.println(new LC443().compress(new char[]{'a'}) == 1);
        System.out.println(new LC443().compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}) == 4);
    }

    public int compress(char[] chars) {
        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                chars[write++] = chars[anchor];
                if (read > anchor) {
                    for (char c: ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }
}
