package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/4/2
 */
public class LC633 {

    public static void main(String[] args) {
        System.out.println(!new LC633().judgeSquareSum(342342532));
        System.out.println(new LC633().judgeSquareSum(5));
        System.out.println(!new LC633().judgeSquareSum(3));
    }

    public boolean judgeSquareSum1(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b)
                return true;
        }
        return false;
    }

    /**
     * todo
     */
    public boolean judgeSquareSum(int c) {
        if (c < 3) return true;
        while (c % 2 == 0) c /= 2;
        int top = (int) Math.round(Math.sqrt(c)) + 1;
        for (int i = 3; i <= top; i += 2) {
            int t = 0;
            while (c % i == 0) {
                t++;
                c /= i;
            }
            if (i % 4 == 3 && t % 2 == 1) return false;
            if (t != 0) top = (int) Math.round(Math.sqrt(c)) + 1;
        }
        return c % 4 != 3;
    }

    public boolean judgeSquareSum3(int c) {
        long l = 0, r = (long) Math.sqrt(c);
        if (c <= 2)
            return true;
        while (l <= r) {
            long res = l * l + r * r;
            if ((res) < c)
                l++;
            else if ((res > c))
                r--;
            else
                return true;
        }
        return false;
    }
}
