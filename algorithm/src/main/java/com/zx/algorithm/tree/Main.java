package com.zx.algorithm.tree;

import java.util.Scanner;

/**
 * @author zhouxin
 * @since 2020/2/6
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer n = sc.nextInt();
        System.out.println(a(n));
    }

    private static boolean a(int n){
        int sum = 0;
        int index = n / 2;
        for (int i = index; i > 0; i--) {
            if(n % i == 0){
                sum += i;
            }
        }
        return sum == n;
    }
}
