package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/3/21
 */
public class LC412 {

    public static void main(String[] args) {
        System.out.println(new LC412().fizzBuzz(15));
    }

    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String str = "";
            if (i % 3 == 0) {
                str += "Fizz";
            }
            if (i % 5 == 0) {
                str += "Buzz";
            }
            if (str.equals("")) {
                list.add(Integer.toString(i));
            } else {
                list.add(str);
            }
        }
        return list;
    }
}
