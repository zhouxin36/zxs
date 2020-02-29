package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/2/29
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC13 {

    public static void main(String[] args) {
        System.out.println(new LC13().romanToInt("III") == 3);
        System.out.println(new LC13().romanToInt("IV") == 4);
        System.out.println(new LC13().romanToInt("IX") == 9);
        System.out.println(new LC13().romanToInt("LVIII") == 58);
        System.out.println(new LC13().romanToInt("MCMXCIV") == 1994);
    }

    public int romanToInt(String s) {
        return romanToInt1(s);
    }

    public int romanToInt1(String s) {
        int sum = 0;
        int prefNum = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer num;
            switch (ch) {
                case 'I':
                    num = 1;
                    break;
                case 'V':
                    num = 5;
                    break;
                case 'X':
                    num = 10;
                    break;
                case 'L':
                    num = 50;
                    break;
                case 'C':
                    num = 100;
                    break;
                case 'D':
                    num = 500;
                    break;
                case 'M':
                    num = 1000;
                    break;
                default:
                    num = 0;
            }
            if (prefNum < num) {
                sum -= prefNum;
            } else {
                sum += prefNum;
            }
            prefNum = num;
        }
        return sum + prefNum;
    }
}
