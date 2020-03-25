package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/25
 */
public class LC509 {

    public static void main(String[] args) {
        System.out.println(new LC509().fib(2) == 1);
        System.out.println(new LC509().fib(3) == 2);
        System.out.println(new LC509().fib(4) == 3);
    }

    public int fib(int N) {
        return fib4(N);
    }

    public int fib1(int N) {
        if (N < 2) {
            return N;
        } else if (N == 2) {
            return 1;
        }
        return fib1(N - 1) + fib1(N - 2);
    }

    public int fib2(int N) {
        if (N < 2) {
            return N;
        } else if (N == 2) {
            return 1;
        }
        int a, b;
        a = 1;
        b = 1;
        for (int i = 2; i < N; i++) {
            int tmp = a;
            a = b;
            b = a + tmp;
        }
        return b;
    }

    public int fib3(int N) {
        if (N < 2) {
            return N;
        } else if (N == 2) {
            return 1;
        }
        double a = Math.pow(5, 0.5);
        return (int) ((Math.pow((a + 1) / 2.0, N) - Math.pow((1 - a) / 2.0, N)) / a);
    }

    public int fib4(int N) {
        if (N < 2) {
            return N;
        } else if (N == 2) {
            return 1;
        }
        return doFib4(N)[0][1];
    }

    public int[][] doFib4(int n) {
        if (n == 1) {
            return new int[][]{{1, 1}, {1, 0}};
        }
        int[][] ints = doFib4(n / 2);
        if (n % 2 == 1) {
            return times(new int[][]{{1, 1}, {1, 0}}, times(ints, ints));
        } else {
            return times(ints, ints);
        }
    }

    public int[][] times(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }
}
