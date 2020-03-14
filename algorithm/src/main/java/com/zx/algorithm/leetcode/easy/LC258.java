package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/14
 */
public class LC258 {

    public static void main(String[] args) {
        System.out.println(new LC258().addDigits(38) == 2);
        System.out.println(new LC258().addDigits(10) == 1);
    }

    public int addDigits(int num) {
        return addDigits2(num);
    }

    public int addDigits1(int num) {
        int pref = 0;
        while (num >= 10){
            while (num != 0){
                pref += num % 10;
                num /= 10;
            }
            num = pref;
            pref = 0;
        }
        return num;
    }

    /**
     * https://en.wikipedia.org/wiki/Digital_root#Congruence_formula
     * 数根
     * @param num
     * @return
     */
    public int addDigits2(int num) {
        return (num - 1) % 9 + 1;
    }

}
