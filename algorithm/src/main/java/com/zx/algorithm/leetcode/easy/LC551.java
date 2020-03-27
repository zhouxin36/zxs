package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/27
 */
public class LC551 {
    public static void main(String[] args) {
        System.out.println(new LC551().checkRecord("LALL"));
        System.out.println(new LC551().checkRecord("PPALLP"));
        System.out.println(!new LC551().checkRecord("PPALLL"));
    }

    public boolean checkRecord(String s) {
        int a = 0;
        int l = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'A'){
                a++;
                l = 0;
            }else if (c == 'L'){
                l++;
            }else {
                l = 0;
            }
            if (a > 1 || l > 2){
                return false;
            }
        }
        return true;
    }
}
