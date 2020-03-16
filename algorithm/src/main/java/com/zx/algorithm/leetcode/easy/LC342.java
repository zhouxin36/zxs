package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/16
 */
public class LC342 {

    public static void main(String[] args) {
        System.out.println(new LC342().isPowerOfFour(16));
        System.out.println(!new LC342().isPowerOfFour(5));
    }

    public boolean isPowerOfFour(int n) {
        return isPowerOfFour1(n);
    }

    public boolean isPowerOfFour1(int n) {
        if (n == 0) {
            return false;
        }
        while (n != 1) {
            if (n % 4 != 0) {
                return false;
            }
            n /= 4;
        }
        return true;
    }

    /**
     * 3 进制
     */
    public boolean isPowerOfFour2(int n) {
        return Integer.toString(n, 4).matches("^10*$");
    }

    /**
     * 对数
     */
    public boolean isPowerOfFour3(int n) {
        return (Math.log10(n) / Math.log10(4)) % 1 == 0;
    }

}
