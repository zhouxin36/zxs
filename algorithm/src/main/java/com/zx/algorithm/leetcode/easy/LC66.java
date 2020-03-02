package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/2
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC66 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC66().plusOne(new int[]{1, 2, 3})).equals("[1, 2, 4]"));
        System.out.println(Arrays.toString(new LC66().plusOne(new int[]{9, 9, 9})).equals("[1, 0, 0, 0]"));
        System.out.println(Arrays.toString(new LC66().plusOne(new int[]{9, 8, 9})).equals("[9, 9, 0]"));
    }

    public int[] plusOne(int[] digits) {
        return plusOne1(digits);
    }

    public int[] plusOne1(int[] digits) {
        int last = digits[digits.length - 1];
        if (last != 9) {
            digits[digits.length - 1] = last + 1;
            return digits;
        }
        // 是否进位
        boolean carry = true;
        for (int i = digits.length - 1; i > 0; i--) {
            digits[i] = 0;
            last = digits[i - 1];
            if (last != 9) {
                digits[i - 1] = last + 1;
                carry = false;
                break;
            }
        }
        if (!carry) {
            return digits;
        }
        int[] newArray = new int[digits.length + 1];
        newArray[0] = 1;
        return newArray;
    }

}
