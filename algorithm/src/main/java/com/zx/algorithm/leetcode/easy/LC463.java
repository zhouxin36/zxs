package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/22
 */
public class LC463 {
    public static void main(String[] args) {
        System.out.println(new LC463().islandPerimeter(new int[][]{{0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}}));
    }

    public int islandPerimeter(int[][] grid) {
        int four = 0;
        int two = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1){
                    four++;
                    if (i > 0 && grid[i - 1][j] == 1){
                        two++;
                    }
                    if (j > 0 && grid[i][j - 1] == 1){
                        two++;
                    }
                }

            }
        }
        return four * 4 - two * 2;
    }
}
