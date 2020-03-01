package com.zx.algorithm.leetcode.easy;

/**
 * 题意是n=1时输出字符串1；n=2时，数上次字符串中的数值个数，因为上次字符串有1个1，所以输出11；n=3时，由于上次字符是11，有2个1，所以输出21；n=4时，由于上次字符串是21，有1个2和1个1，所以输出1211。依次类推，写个countAndSay(n)函数返回字符串。
 *
 * @author zhouxin
 * @since 2020/3/1
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC38 {

    public static void main(String[] args) {

        System.out.println(new LC38().countAndSay(1).equals("1"));
        System.out.println(new LC38().countAndSay(2).equals("11"));
        System.out.println(new LC38().countAndSay(3).equals("21"));
        System.out.println(new LC38().countAndSay(4).equals("1211"));
        System.out.println(new LC38().countAndSay(5).equals("111221"));
    }

    public String countAndSay(int n) {
        return countAndSay2(n);
    }

    /**
     * 递归
     */
    public String countAndSay1(int n) {
        if (n == 1) {
            return "1";
        }
        String str = countAndSay1(n - 1);
        StringBuilder sb = new StringBuilder();
        int index = 1;
        char pref = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (pref == str.charAt(i)) {
                index++;
            } else {
                sb.append(index).append(pref);
                pref = str.charAt(i);
                index = 1;
            }
        }
        sb.append(index).append(pref);
        return sb.toString();
    }

    /**
     * 循环
     */
    public String countAndSay2(int n) {
        if (n == 1) {
            return "1";
        }
        String str = "1";
        StringBuilder sb = new StringBuilder();
        int num = 1;
        while (num < n) {
            int index = 1;
            char pref = str.charAt(0);
            for (int i = 1; i < str.length(); i++) {
                if (pref == str.charAt(i)) {
                    index++;
                } else {
                    sb.append(index).append(pref);
                    pref = str.charAt(i);
                    index = 1;
                }
            }
            sb.append(index).append(pref);
            str = sb.toString();
            sb = new StringBuilder();
            num++;
        }
        return str;
    }
}
