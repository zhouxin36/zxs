package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/23
 */
public class LC476 {

    public static void main(String[] args) {
        System.out.println(new LC476().findComplement(5) == 2);
        System.out.println(new LC476().findComplement(1) == 0);
    }

    public int findComplement(int num) {
        int n = num;
        int pref = 0;
        while (n != 0){
            pref = n & -n;
            n = n & (n - 1);
        }
        return ((pref << 1) - 1) ^ num;
    }
}
