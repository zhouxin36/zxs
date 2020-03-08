package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/8
 */
public class LC172 {

    public static void main(String[] args) {
        System.out.println(new LC172().trailingZeroes(13) == 2);
        System.out.println(new LC172().trailingZeroes(7) == 1);
        System.out.println(new LC172().trailingZeroes(3) == 0);
        System.out.println(new LC172().trailingZeroes(5) == 1);
    }

    /**
     *
     1.需要注意的是后缀0是由2，5相乘得来，因此只需看有多少个2，5即可
     n = 5: 5!的质因子中 (2 * 2 * 2 * 3 * 5)包含一个5和三个2。因而后缀0的个数是1。
     n = 11: 11!的质因子中(2^8 * 3^4 * 5^2 * 7)包含两个5和三个2。于是后缀0的个数就是2。

     2质因子中2的个数总是大于等于5的个数。因此只要计数5的个数就可以了。

     例如： 11中有两个5因此输出2.可用 n/5=2;

     3.需要注意的是25中有25，20，15，10，5，但是25又可以分为5*5，
     因此需要判断t=n/5后中t的5个数
     */
    public int trailingZeroes(int n) {
        if (n <= 4){
            return 0;
        }
        int count=0;
        while (n > 4) {     //count the number of factor 5;
            count+=n/5;
            n/=5;
        }
        return count;
    }
}
