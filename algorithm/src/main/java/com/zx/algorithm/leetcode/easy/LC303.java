package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/16
 */
public class LC303 {

    public static void main(String[] args) {
        int[] arr = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray2 numArray = new NumArray2(arr);
        System.out.println(numArray.sumRange(0, 2) == 1);
        System.out.println(numArray.sumRange(2, 5) == -1);
        System.out.println(numArray.sumRange(0, 5) == -3);
    }

    static class NumArray {

        private int[] arr;

        public NumArray(int[] nums) {
            this.arr = nums;
        }

        public int sumRange(int i, int j) {
            if (i < 0) {
                i = 0;
            }
            if (j > arr.length - 1) {
                j = arr.length - 1;
            }
            if (i > j) {
                return 0;
            }
            int sum = 0;
            for (int k = i; k <= j; k++) {
                sum += arr[k];
            }
            return sum;
        }
    }

    static class NumArray2 {

        private int[] arr;

        public NumArray2(int[] nums) {
            this.arr = new int[nums.length + 1];
            arr[0] = 0;
            for (int i = 1; i <= nums.length; i++) {
                arr[i] = arr[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int i, int j) {
            return arr[j + 1] - arr[i];
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
}
