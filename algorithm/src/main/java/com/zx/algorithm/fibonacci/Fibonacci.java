package com.zx.algorithm.fibonacci;

import java.util.Stack;

/**
 * 斐波拉契数列
 *
 * @author zhouxin
 * @since 2020/1/8
 */
public class Fibonacci {

    public static void main(String[] args){
        for (int i = 1; i < 10; i++) {
//            System.out.println(sum(i));
            System.out.println(sum2(i) + ":");
            System.out.println(sum3(i) + ":");
        }

    }
    public static int sum(int n){
        if(n == 1){
            return 1;
        }else if(n == 2){
            return 2;
        }else {
            return sum(n - 1) + sum(n - 2);
        }
    }

    public static int sum2(int n){
        Stack<Integer> stack = new Stack<>();
        if(n == 1){
            return 1;
        }else {
            stack.push(1);
        }
        if(n == 2){
            return 2;
        }else {
            stack.push(2);
        }
        for (int i = 2; i < n; i++) {
            int a = stack.pop();
            int b = stack.pop();
            stack.push(a);
            stack.push(a + b);
        }
        return stack.pop();
    }

    public static int sum3(int n){
        if(n < 4){
            return n;
        }else {
            double a = Math.pow(5.0, 0.5);
            return (int )((Math.pow((1 + a) / 2.0, n + 1) - Math.pow((1 - a) / 2.0, n + 1)) / a);
        }
    }
}
