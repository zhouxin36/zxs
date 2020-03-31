package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/31
 */
public class LC605 {

    public static void main(String[] args) {
        System.out.println(new LC605().canPlaceFlowers(new int[]{1,0,0,0,1}, 1));
        System.out.println(!new LC605().canPlaceFlowers(new int[]{1,0,0,0,1}, 2));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n <= 0){
            return true;
        }
        int p, r;
        for (int i = 0; i < flowerbed.length && n > 0; i++) {
            if (flowerbed[i] != 1){
                if (i == 0){
                    p = 0;
                }else {
                    p = flowerbed[i - 1];
                }
                if (i == flowerbed.length - 1){
                    r = 0;
                }else {
                    r = flowerbed[i + 1];
                }
                if (p ==0 && r == 0){
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }
        return n == 0;
    }
}
