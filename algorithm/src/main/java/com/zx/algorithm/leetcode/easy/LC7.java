package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/2/29
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC7 {

    public static void main(String[] args) {
        System.out.println(new LC7().reverse(-120300));
        System.out.println(new LC7().reverse(-0));
        System.out.println(new LC7().reverse(0));
        System.out.println(new LC7().reverse(1534236469));
        System.out.println(new LC7().reverse(1563847412));
    }

    public int reverse(int x) {
        return reverse2(x);
    }

    /**
     * 子串拼接
     */
    public int reverse1(int x) {
        StringBuilder sb = new StringBuilder();
        boolean negative = false;
        if (x < 0) {
            sb.append("-");
            negative = true;
        }
        boolean zero = true;
        do {
            int a = x % 10;
            if (!zero || a != 0) {
                sb.append(negative ? -a : a);
                zero = false;
            }
        } while ((x = x / 10) != 0);
        try {
            return Integer.parseInt(sb.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    private static final int max = Integer.MAX_VALUE / 10;
    private static final int min = Integer.MIN_VALUE / 10;
    public int reverse2(int x) {
        if (x < 10 && x > -10) {
            return x;
        }
        int sum = 0;
        while (x != 0) {
            int remainder = x % 10;
            if (sum > max || (sum == max && remainder > 7) || sum < min || (sum == min && remainder < -8)) {
                return 0;
            }
            sum = sum * 10 + x % 10;
            x /= 10;
        }
        return sum;
    }
}
