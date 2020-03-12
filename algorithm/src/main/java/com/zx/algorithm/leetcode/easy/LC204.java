package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/10
 */
public class LC204 {
    public static void main(String[] args) {
//        System.out.println(new LC204().countPrimes(0) == 0);
//        System.out.println(new LC204().countPrimes(1) == 0);
//        System.out.println(new LC204().countPrimes(2) == 0);
//        System.out.println(new LC204().countPrimes(3) == 1);
        System.out.println(new LC204().countPrimes(10) == 4);
        System.out.println(new LC204().countPrimes(1001) == 168);
    }

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] is = new boolean[n];
        for (int i = 4; i < n; i += 2) {
            is[i] = true;
        }
        for (int i = 3; i * i < n; i += 2) {
            if (!is[i]) {
                for (int j = i * i; j < n; j += i * 2) {
                    is[j] = true;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!is[i]) {
                count++;
            }
        }
        return count;
    }

}
