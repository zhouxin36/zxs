package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/8
 */
public class LC189 {
    public static void main(String[] args) {
        int[] arr0 = new int[]{1, 2, 3, 4, 5, 6};
        new LC189().rotate(arr0, 4);
        System.out.println(Arrays.toString(arr0).equals(Arrays.toString(new int[]{3, 4, 5, 6, 1, 2})));

        arr0 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};
        new LC189().rotate(arr0, 38);
        System.out.println(Arrays.toString(arr0).equals(Arrays.toString(new int[]{17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16})));

        arr0 = new int[]{1, 2, 3, 4, 5, 6, 7};
        new LC189().rotate(arr0, 3);
        System.out.println(Arrays.toString(arr0).equals(Arrays.toString(new int[]{5, 6, 7, 1, 2, 3, 4})));

        arr0 = new int[]{1, 2, 3, 4, 5, 6, 7};
        new LC189().rotate(arr0, 4);
        System.out.println(Arrays.toString(arr0).equals(Arrays.toString(new int[]{4, 5, 6, 7, 1, 2, 3})));

        arr0 = new int[]{-1, -100, 3, 99};
        new LC189().rotate(arr0, 2);
        System.out.println(Arrays.toString(arr0).equals(Arrays.toString(new int[]{3, 99, -1, -100})));
    }

    /**
     * 数字交换函数12
     */
    private static void swap2(int[] nums, int a, int b) {
        // num1^=num2^=num1^=num2;
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }

    /**
     * 数字交换函数1
     */
    private static void swap(int[] nums, int a, int b) {
        nums[a] = nums[a] + nums[b];
        nums[b] = nums[a] - nums[b];
        nums[a] = nums[a] - nums[b];
    }

    public void rotate(int[] nums, int k) {
        rotate3(nums, k);
    }

    public void rotate1(int[] nums, int k) {
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(arr, 0, nums, 0, nums.length);
    }

    public void rotate2(int[] nums, int k) {
        int size = nums.length - 1;
        while (k > 0) {
            int m = nums[size];
            for (int i = size - 1; i >= 0; i--) {
                nums[i + 1] = nums[i];
            }
            nums[0] = m;
            k--;
        }
    }

    public void rotate3(int[] nums, int k) {
        if (k < 1 || k == nums.length) {
            return;
        }
        rotate3(nums, k, 0, nums.length - 1);
    }

    public void rotate3(int[] nums, int k, int start, int end) {
        int num = end - start + 1;
        if (k > num) {
            k = k % num;
        }
        // 把后面的k个数直接和前面交换
        for (int i = start, j = end - k + 1; i <= end && j <= end; i++, j++) {
            swap2(nums, i, j);
        }
        // 如果对半，就不需要继续进行
        if (num == 2 * k || num - k == 1 || num % (num - k) == 0) {
            return;
        }
        int a;
        if (2 * k > num) {
            a = Math.min(k, num - 2 * (num - k));
        } else {
            a = k;
        }
        rotate3(nums, a, start + k, end);
    }


    public void rotate4(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
