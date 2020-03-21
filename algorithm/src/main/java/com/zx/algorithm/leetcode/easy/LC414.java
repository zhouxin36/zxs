package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/21
 */
public class LC414 {
    public static void main(String[] args) {
        System.out.println(new LC414().thirdMax(new int[]{1, -2147483648, 2}) == -2147483648);
        System.out.println(new LC414().thirdMax(new int[]{3, 2, 1}) == 1);
        System.out.println(new LC414().thirdMax(new int[]{1, 2, 2, 5, 3, 5}) == 2);
        System.out.println(new LC414().thirdMax(new int[]{1, 2}) == 2);
        System.out.println(new LC414().thirdMax(new int[]{2, 2, 3, 1}) == 1);
    }

    public int thirdMax(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = max1, max3 = max1;
        int length = 0;
        boolean is = false;
        for (int num : nums) {
            if (num == Integer.MIN_VALUE && num == max3){
                is = true;
            }
            if (num > max1) {
                length++;
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2 && num != max1) {
                length++;
                max3 = max2;
                max2 = num;
            } else if (num >= max3 && num != max2 && num != max1) {
                length++;
                max3 = num;
            }
        }
        if (length < 3 && is){
            length++;
        }
        return length < 3 ? max1 : max3;
    }
}
