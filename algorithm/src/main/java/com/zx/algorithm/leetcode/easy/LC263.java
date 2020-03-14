package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/14
 */
public class LC263 {

    public static void main(String[] args) {
        System.out.println(new LC263().isUgly(6));
        System.out.println(new LC263().isUgly(8));
        System.out.println(!new LC263().isUgly(14));
    }

    public boolean isUgly(int num) {
        return isUgly1(num);
    }

    public boolean isUgly1(int num) {
        if (num == 0){
            return false;
        }
        while (num != 1){
            if (num % 2 == 0){
                num /= 2;
            }else if (num % 3 == 0){
                num /= 3;
            }else if (num % 5 == 0){
                num /= 5;
            }else {
                return false;
            }
        }
        return true;
    }
}
