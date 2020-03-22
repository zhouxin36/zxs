package com.zx.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @since 2020/3/22
 */
public class LC447 {

    public static void main(String[] args) {
        System.out.println(new LC447().numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}}) == 2);
    }

    public int numberOfBoomerangs(int[][] points) {
        return numberOfBoomerangs2(points);
    }

    public int numberOfBoomerangs2(int[][] points) {
        if (points.length < 3) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int x = points[i][0];
            int y = points[i][1];
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int d1 = (x - points[j][0]) * (x - points[j][0]) + (y - points[j][1]) * (y - points[j][1]);
                if (!map.containsKey(d1)) {
                    map.put(d1, 1);
                } else {
                    int a = map.get(d1);
                    count += 2 * a;
                    map.put(d1, a + 1);
                }
            }
        }
        return count;
    }

    public int numberOfBoomerangs1(int[][] points) {
        if (points.length < 3) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int d1 = disSquare(points, i, j);
                for (int k = j; k < points.length; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    if (d1 == disSquare(points, i, k)) {
                        count += 2;
                    }
                }
            }
        }
        return count;
    }

    public int disSquare(int[][] points, int i, int j) {
        int x = points[i][0] - points[j][0];
        int y = points[i][1] - points[j][1];
        return x * x + y * y;
    }
}
