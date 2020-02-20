package com.zx.algorithm.exp.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * (霍纳(Horner)规则的正确性)
 * 编写伪代码来实现朴素的多项式求值算法，该算法从头开始计算多项式的每个项。该算法的运行时间是多少？
 * @author zhouxin
 * @since 2020/1/7
 */
public class TwoThree {

    public static void main(String[] args) {
        List<Integer> list1;
        int x = 10;
        list1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(horner1(new ArrayList<>(list1), x));
        System.out.println(horner2(new ArrayList<>(list1), x));
    }

    private static int horner1(List<Integer> list, int x) {
        int size = list.size();
        int sum = 0;
        for (int i = size - 1; i >= 0; i--) {
            sum = list.get(i) + sum * x;
        }
        return sum;
    }

    private static int horner2(List<Integer> list, int x) {
        int sum = 0;
        int xn = 1;
        for (Integer integer : list) {
            sum += integer * xn;
            xn *= x;
        }
        return sum;
    }

}
