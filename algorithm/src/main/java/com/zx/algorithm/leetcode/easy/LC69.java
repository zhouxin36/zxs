package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/2
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC69 {

    public static void main(String[] args) {
        System.out.println(new LC69().mySqrt(183692038) == 13553);
        System.out.println(new LC69().mySqrt(2147395599) == 46339);
        System.out.println(new LC69().mySqrt(4) == 2);
        System.out.println(new LC69().mySqrt(8) == 2);
    }

    public int mySqrt(int x) {
        return mySqrt3(x);
    }

    public int mySqrt1(int x) {
        return (int) Math.sqrt(x);
    }

    public int mySqrt2(int x) {
        if (x < 2) {
            return x;
        }
        int start = 0;
        int end = x;
        int mid = x >> 1;
        if (mid > 46340) {
            mid = 46340;
            start = 0;
            end = mid;
        }
        while (end - start > 1) {
            if (mid * mid > x) {
                end = mid;
            } else {
                start = mid;
            }
            mid = (start + end) >> 1;

        }
        return mid;
    }

    /**
     * 牛顿迭代法
     * <p>令 f(x) = x^2 - n</p>
     * <p>f'(x) = 2x = (f(x) - f(x0)) / (x - x0)</p>
     * <p>f(x0) = 0</p>
     * <p>2x^2 - 2xx0 = x^2 - n</p>
     * <p>x0 = (x + n/x)/2 </p>
     */
    public int mySqrt3(int x) {
        if (x < 2) {
            return x;
        }
        double last = 0.0;
        double res = 1.0;
        while (res != last) {
            last = res;
            res = (res + x / res) / 2;
        }
        return (int) res;
    }

}
