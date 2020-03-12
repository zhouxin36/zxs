package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/12
 */
public class LC231 {

    public static void main(String[] args) {
        System.out.println(new LC231().isPowerOfTwo(1));
        System.out.println(new LC231().isPowerOfTwo(16));
        System.out.println(!new LC231().isPowerOfTwo(218));
    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0){
            return false;
        }
        return isPowerOfTwo2(n);
    }
    public boolean isPowerOfTwo1(int n) {
        return (n & (n - 1)) == 0;
    }
    public boolean isPowerOfTwo2(int n) {
        return n == (n & (-n));
    }
}
