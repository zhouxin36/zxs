package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/16
 */
public class LC326 {

    private static final int MAX_VAL = (int) Math.pow(3, (int)(Math.log10(Integer.MAX_VALUE) / Math.log10(3)));

    public static void main(String[] args) {
        System.out.println(new LC326().isPowerOfThree(27));
        System.out.println(!new LC326().isPowerOfThree(0));
        System.out.println(new LC326().isPowerOfThree(9));
        System.out.println(!new LC326().isPowerOfThree(45));
    }

    public boolean isPowerOfThree(int n) {
        return isPowerOfThree4(n);
    }

    public boolean isPowerOfThree1(int n) {
        if (n == 0) {
            return false;
        }
        while (n != 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }

    /**
     * 3 进制
     */
    public boolean isPowerOfThree2(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

    /**
     * 对数
     */
    public boolean isPowerOfThree3(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    /**
     * 计算出最大的3的幂
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree4(int n) {
        return n > 0 && MAX_VAL % n == 0;
    }
}
