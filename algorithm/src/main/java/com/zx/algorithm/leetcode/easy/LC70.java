package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/3
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC70 {
    public static void main(String[] args) {
        System.out.println(new LC70().climbStairs(3) == 3);
        System.out.println(new LC70().climbStairs(8) == 34);
        System.out.println(new LC70().climbStairs(19) == 6765);
    }

    private static int[][] times(int[][] arr1, int[][] arr2) {
        int[][] result = new int[arr1.length][arr2[0].length];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                for (int k = 0; k < arr1[0].length; k++) {
                    result[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return result;
    }

    public int climbStairs(int n) {
        return climbStairs3(n);
    }

    public int climbStairs1(int n) {
        if (n < 4) {
            return n;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    public int climbStairs2(int n) {
        if (n < 4) {
            return n;
        }
        int a = 1;
        int b = 2;
        int tmp;
        for (int i = 2; i < n; i++) {
            tmp = a;
            a = b;
            b = tmp + a;
        }
        return b;
    }

    public int climbStairs3(int n) {
        if (n < 4) {
            return n;
        }
        int[][] arr = new int[][]{{1, 1}, {1, 0}};
        return doClimbStairs3(arr, n + 1)[0][1];
    }

    public int[][] doClimbStairs3(int[][] arr, int n) {
        if (n == 1) {
            return arr;
        }
        int[][] array = doClimbStairs3(arr, n / 2);
        if (n % 2 == 0) {
            return times(array, array);
        } else {
            return times(arr, times(array, array));
        }
    }
}
