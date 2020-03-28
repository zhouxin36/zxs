package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/28
 */
public class LC566 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new LC566().matrixReshape(new int[][]{{1, 2}, {3, 4}}, 1, 4)).equals("[[1, 2, 3, 4]]"));
        System.out.println(Arrays.deepToString(new LC566().matrixReshape(new int[][]{{1, 2}, {3, 4}}, 2, 4)).equals("[[1, 2], [3, 4]]"));
    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null || nums.length == 0 || nums[0].length == 0 || nums.length * nums[0].length != r * c){
            return nums;
        }
        int[][] arr = new int[r][c];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                int a = i * nums[0].length + j;
                arr[a / c][a % c] = nums[i][j];
            }
        }
        return arr;
    }
}
