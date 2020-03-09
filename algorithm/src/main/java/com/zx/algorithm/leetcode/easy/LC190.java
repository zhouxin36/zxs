package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/9
 */
public class LC190 {


    public static void main(String[] args) {
        System.out.println(new LC190().reverseBits(0b00000010100101000001111010011100) == 0b00111001011110000010100101000000);
        System.out.println(new LC190().reverseBits(0b11111111111111111111111111111101) == 0b10111111111111111111111111111111);
    }

    public int reverseBits(int n) {
        int a = 0b00000000000000000000000000000001;
        int b = 0b10000000000000000000000000000000;
        for (int i = 0; i < 16; i++) {
            int a1 = n & a;
            int b1 = n & b;
            if (a1 != b1){
                if (a1 == 0){
                    n = n - b;
                }else {
                    n = b | n;
                }
                if (b1 == 0){
                    n = n - a;
                }else {
                    n = a | n;
                }
            }
            a <<= 1;
            if (i == 0){
                b = (b>> 1) - b;
            }else {
                b >>= 1;
            }
        }
        return n;
    }

}
