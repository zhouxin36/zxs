package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/18
 */
public class LC371 {

    public static void main(String[] args) {
        System.out.println(new LC371().getSum(555, 556) == Integer.sum(555, 556));
        System.out.println(new LC371().getSum(1, 2) == 3);
        System.out.println(new LC371().getSum(-2, 3) == 1);
    }

    public int getSum(int a, int b) {
        while (b != 0) {
            //filter out identical component
            int carry = a & b;
            //filter out complementary component
            a = a ^ b;

            //shift b to the left the common components. and assign it to b
            //on the next iteration there will be fewer and fewer common components
            //and gradually b will converge to 0 conce we have shifted the integer left
            //the appropriate amount of times.
            b = carry << 1;
        }
        return a;
    }
}
