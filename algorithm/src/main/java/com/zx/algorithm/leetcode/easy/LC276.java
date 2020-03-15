package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/15
 */
public class LC276 {

    public static void main(String[] args) {
        System.out.println(new LC276().numWays1(3, 2) == 6);
        System.out.println(new LC276().numWays1(3, 2) + "|" + new LC276().numWays2(3,2));
        System.out.println(new LC276().numWays1(3, 3) + "|" + new LC276().numWays2(3,3));
        System.out.println(new LC276().numWays1(3, 4) + "|" + new LC276().numWays2(3,4));
        System.out.println(new LC276().numWays1(6, 8) + "|" + new LC276().numWays2(6,8));
    }

    public int numWays1(int n, int k) {
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return k;
        }
        if ( n == 2){
            return k * k;
        }
        return (numWays1(n - 1, k) + numWays1(n - 2, k)) * (k - 1);
    }

    /**
     * @param n 墙数
     * @param k 颜色数
     * @return f(n) = f(n - 1) * (k - 1) + f(n - 2) * (k - 1)
     */
    public int numWays2(int n, int k) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        int same = k, aDiff = k * (k - 1), bDiff = aDiff;
        for (int i = 2; i < n; i++) {
            bDiff = (aDiff + same) * (k - 1);
            same = aDiff;
            aDiff = bDiff;
        }
        return same + bDiff;
    }


}
