package com.zx.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * @author zhouxin
 * @since 2020/3/23
 */
public class LC492 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC492().constructRectangle(10000000)).equals("[3200, 3125]"));
        System.out.println(Arrays.toString(new LC492().constructRectangle(16)).equals("[4, 4]"));
        System.out.println(Arrays.toString(new LC492().constructRectangle(12)).equals("[4, 2]"));
        System.out.println(Arrays.toString(new LC492().constructRectangle(4)).equals("[2, 2]"));
        for (int i = 0; i < 100; i++) {
            System.out.println("n=" + i + ":" + Arrays.toString(new LC492().constructRectangle(i)));
        }
    }

    public int[] constructRectangle(int area) {
        int[] result = new int[2];
        if (area == 0) {
            return result;
        }
        int a = (int) Math.sqrt(area);
        while (area % a != 0) {
            a--;
        }
        int b = area / a;
        result[0] = b;
        result[1] = a;
        return result;
    }
}
