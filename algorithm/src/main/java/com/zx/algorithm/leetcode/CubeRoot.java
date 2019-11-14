package com.zx.algorithm.leetcode;

/**
 * 牛顿迭代法。设f(x)=x3-y, 求f(x)=0时的解x，即为y的立方根。
 * 根据牛顿迭代思想，xn+1=xn-f(xn)/f'(xn)即x=x-(x3-y)/(3*x2)=(2*x+y/x/x)/3;
 *
 * @author zhouxin
 * @since 2019/7/1
 */
public class CubeRoot {

    static double abs(double x){return (x>0?x:-x);}

    static double cubert(double y){
        double x;
        for(x=1.0;abs(x*x*x-y)>1e-7;x=(2*x+y/x/x)/3);
        return x;
    }

    public static void main(String[] args) {
        System.out.println(cubert(8));
    }
}
