package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/4/1
 */
public class LC624 {

    public static void main(String[] args) {
        System.out.println(new LC624().maxDistance(new int[][]{{1, 2, 3},
                {4, 5},
                {1, 2, 3}}) == 4);
    }

    public int maxDistance(int[][] arr) {
        int max, sMax, min, sMin, maxIndex = -1, minIndex = -1;
        max = sMax = Integer.MIN_VALUE;
        min = sMin = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (min >= arr[i][0]) {
                sMin = min;
                min = arr[i][0];
                minIndex = i;
            }
            if (max <= arr[i][arr[i].length - 1]) {
                sMax = max;
                max = arr[i][arr[i].length - 1];
                maxIndex = i;
            }
        }
        if (maxIndex != minIndex){
            return max - min;
        }else {
            return Math.max(max - sMin, sMax - min);
        }
    }
}
