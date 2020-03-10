package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/10
 */
public class LC202 {

    public static void main(String[] args) {
        System.out.println(new LC202().isHappy(19));
    }

    public boolean isHappy(int n) {
        int sum = n;
        for (int i = 0; i < 10; i++) {
            n = sum;
            sum = 0;
            while (n != 0) {
                int a = n % 10;
                sum += a * a;
                n = n / 10;
            }
            if (sum == 1){
                return true;
            }
        }
        return false;
    }
}
