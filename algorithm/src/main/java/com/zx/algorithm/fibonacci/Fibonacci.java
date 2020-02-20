package com.zx.algorithm.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * 斐波拉契数列
 *
 * @author zhouxin
 * @since 2020/1/8
 */
@SuppressWarnings("WeakerAccess")
public class Fibonacci {

    private static final Logger logger = LoggerFactory.getLogger(Fibonacci.class);

    public static void main(String[] args){
        for (int i = 1; i < 20; i++) {
            logger.info("递归：{} - 遍历:{} - 公式:{} - 矩阵:{}", sum(i), sum2(i), sum3(i), sum4(i));
        }

    }

    /**
     * 递归
     */
    public static int sum(int n){
        if(n == 1){
            return 1;
        }else if(n == 2){
            return 2;
        }else {
            return sum(n - 1) + sum(n - 2);
        }
    }

    /**
     * 遍历
     */
    public static int sum2(int n){
        int a, b;
        if(n == 1){
            return 1;
        }else {
            a = 1;
        }
        if(n == 2){
            return 2;
        }else {
            b = 2;
        }
        for (int i = 2; i < n; i++) {
            int tmp = a;
            a = b;
            b = tmp + b;
        }
        return b;
    }


    /**
     * 公式
     */
    public static int sum3(int n){
        if(n < 4){
            return n;
        }else {
            double a = Math.pow(5.0, 0.5);
            return (int )((Math.pow((1 + a) / 2.0, n + 1) - Math.pow((1 - a) / 2.0, n + 1)) / a);
        }
    }

    /**
     * 矩阵
     * {1,1}n次方
     * {1,0}
     * =>
     * {f(n+1), fn}
     * {fn, f(n-1)}
     * =>list
     * {0,1}
     * {2,3}
     *
     * x的n次方=｛x的n/2次方 * x的n/2次方    n为偶数
     * x的n次方=｛x的(n-1)/2次方 * x的(n-1)/2次方 * n    n为奇数
     *
     */
    public static final List<Integer> square = Arrays.asList(1, 1, 1, 0);
    public static int sum4(int n){
        return recursive(n + 1).get(1);
    }

    /**
     * 递归求n次方
     * x的n次方=｛x的n/2次方 * x的n/2次方    n为偶数
     * x的n次方=｛x的(n-1)/2次方 * x的(n-1)/2次方 * n    n为奇数
     */
    public static List<Integer> recursive(int n){
        if(n == 1){
            return square;
        }
        if(n % 2 == 0){
            List<Integer> result = recursive(n / 2);
            return times(result, result);
        }else {
            List<Integer> result = recursive(n / 2);
            return times(square, times(result, result));
        }
    }

    public static List<Integer> times(List<Integer> list1, List<Integer> list2){
        return Arrays.asList(list1.get(0) * list2.get(0) + list1.get(1) * list2.get(2)
        ,list1.get(0) * list2.get(1) + list1.get(1) * list2.get(3)
        ,list1.get(2) * list2.get(0) + list1.get(3) * list2.get(2)
        ,list1.get(2) * list2.get(1) + list1.get(3) * list2.get(3));
    }
}
