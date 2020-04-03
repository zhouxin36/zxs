package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/4/3
 */
public class LC661 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new LC661().imageSmoother(new int[][]{{1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}}
        )));
    }

    public int[][] imageSmoother(int[][] M) {
        int R = M.length, C = M[0].length;
        int[][] ans = new int[R][C];
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                int count = 0;
                for (int nr = r - 1; nr <= r + 1; ++nr)
                    for (int nc = c - 1; nc <= c + 1; ++nc) {
                        if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                            ans[r][c] += M[nr][nc];
                            count++;
                        }
                    }
                ans[r][c] /= count;
            }
        return ans;
    }

    public int[][] imageSmoother2(int[][] M) {
        int[][] res = new int[M.length][M[0].length];
        int xlen = M.length;
        int ylen = M[0].length;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                int sum = 0, cnt = 0;
                sum += M[i][j];
                if (i + 1 < xlen) {
                    sum += M[i + 1][j];
                    cnt++;
                }
                if (i - 1 >= 0) {
                    sum += M[i - 1][j];
                    cnt++;
                }
                if (j + 1 < ylen) {
                    sum += M[i][j + 1];
                    cnt++;
                }
                if (j - 1 >= 0) {
                    sum += M[i][j - 1];
                    cnt++;
                }
                if (i + 1 < xlen && j + 1 < ylen) {
                    sum += M[i + 1][j + 1];
                    cnt++;
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    sum += M[i - 1][j - 1];
                    cnt++;
                }
                if (i + 1 < xlen && j - 1 >= 0) {
                    sum += M[i + 1][j - 1];
                    cnt++;
                }
                if (i - 1 >= 0 && j + 1 < ylen) {
                    sum += M[i - 1][j + 1];
                    cnt++;
                }
                res[i][j] = (int) Math.floor(sum / (cnt + 1));
            }
        }
        return res;
    }
}
