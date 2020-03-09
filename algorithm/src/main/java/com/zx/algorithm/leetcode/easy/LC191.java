package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/9
 */
public class LC191 {

    public static void main(String[] args) {
        System.out.println(new LC191().hammingWeight(0b00000000000000000000000000001011) == 3);
        System.out.println(new LC191().hammingWeight(0b00000000000000000000000010000000) == 1);
        System.out.println(new LC191().hammingWeight(0b11111111111111111111111111111101) == 31);
    }

    public int hammingWeight(int n) {
        int a = 0b10000000000000000000000000000000;
        int num = 1;
        int count = 0;
        if ((a & n) != 0) {
            count++;
            n = n - a;
        }
        for (int i = 0; i < 31; i++) {
            if ((n & num) == 1) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }
}
