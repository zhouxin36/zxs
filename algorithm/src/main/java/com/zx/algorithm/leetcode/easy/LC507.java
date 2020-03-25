package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/25
 */
public class LC507 {

    public static void main(String[] args) {
        System.out.println(new LC507().checkPerfectNumber(6));
        System.out.println(new LC507().checkPerfectNumber(28));
        System.out.println(new LC507().checkPerfectNumber(496));
        System.out.println(new LC507().checkPerfectNumber(8128));
        System.out.println(new LC507().checkPerfectNumber(33550336));
    }

    public boolean checkPerfectNumber(int num) {
        return checkPerfectNumber2(num);
    }
    public boolean checkPerfectNumber1(int num) {
        switch (num){
            case 6:
            case 28:
            case 496:
            case 8128:
            case 33550336:
                return true;
            default:
                return false;
        }
    }

    public boolean checkPerfectNumber2(int num) {
        if (num < 6){
            return false;
        }
        int sum = 1;
        int sq = (int)Math.sqrt(num);
        for (int i = 2; i <= sq; i++) {
            if (num % i == 0){
                sum += i;
                sum += num / i;
            }
        }
        return sum == num;
    }
}
