package com.zx.algorithm.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 斐波拉契数列
 *
 * @author zhouxin
 * @since 2020/1/8
 */
@SuppressWarnings("WeakerAccess")
public class Fibonacci {

    /**
     * 矩阵
     * {1,1}n次方
     * {1,0}
     * =>
     * {f(n+1), fn}
     * {fn, f(n-1)}
     * =>list
     * {0,1}
     * {2,3}
     * <p>
     * x的n次方=｛x的n/2次方 * x的n/2次方    n为偶数
     * x的n次方=｛x的(n-1)/2次方 * x的(n-1)/2次方 * n    n为奇数
     */
    public static final int[][] square = {{1, 1}, {1, 0}};
    private static final Logger logger = LoggerFactory.getLogger(Fibonacci.class);

    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            logger.info("递归：{} - 递归（备忘录）:{} - 遍历:{} - 公式:{} - 矩阵:{}", sum(i), sum_(i), sum2(i), sum3(i), sum4(i));
        }

    }

    /**
     * 递归
     */
    public static int sum(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return sum(n - 1) + sum(n - 2);
        }
    }

    /**
     * 递归（备忘录）
     */
    public static int sum_(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 2;
        return doSum_(n, arr);
    }

    public static int doSum_(int n, int[] arr) {
        if (arr[n - 1] != 0){
            return arr[n - 1];
        }
        int num = doSum_(n - 1, arr) + doSum_(n - 2, arr);
        arr[n - 1] = num;
        return num;
    }

    /**
     * 遍历
     */
    public static int sum2(int n) {
        int a, b;
        if (n == 1) {
            return 1;
        } else {
            a = 1;
        }
        if (n == 2) {
            return 2;
        } else {
            b = 2;
        }
        for (int i = 2; i < n; i++) {
            int tmp = a;
            a = b;
            b = tmp + b;
        }
        return b;
    }

    /**
     * 公式
     */
    public static int sum3(int n) {
        if (n < 4) {
            return n;
        } else {
            double a = Math.pow(5.0, 0.5);
            return (int) ((Math.pow((1 + a) / 2.0, n + 1) - Math.pow((1 - a) / 2.0, n + 1)) / a);
        }
    }

    public static int sum4(int n) {
        return recursive(n + 1)[0][1];
    }

    /**
     * 递归求n次方
     * x的n次方=｛x的n/2次方 * x的n/2次方    n为偶数
     * x的n次方=｛x的(n-1)/2次方 * x的(n-1)/2次方 * n    n为奇数
     */
    public static int[][] recursive(int n) {
        if (n == 1) {
            return square;
        }
        if (n % 2 == 0) {
            int[][] result = recursive(n / 2);
            return times(result, result);
        } else {
            int[][] result = recursive(n / 2);
            return times(square, times(result, result));
        }
    }

    public static int[][] times(int[][] arr1, int[][] arr2) {
        int[][] result = new int[arr1.length][arr2[0].length];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                for (int k = 0; k < arr2.length; k++) {
                    result[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return result;
    }
}
