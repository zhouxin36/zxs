package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/21
 */
public class LC415 {

    public static void main(String[] args) {
        System.out.println(new LC415().addStrings("9", "98").equals("107"));
        System.out.println(new LC415().addStrings("9", "99").equals("108"));
        System.out.println(new LC415().addStrings("7", "3").equals("10"));
        System.out.println(new LC415().addStrings("2", "3").equals("5"));
    }

    public String addStrings(String num1, String num2) {
        String min = num1.length() > num2.length() ? num2 : num1;
        String max = num1.length() <= num2.length() ? num2 : num1;
        int a = max.length() - min.length();
        boolean jw = false;
        StringBuilder sb = new StringBuilder();
        for (int i = min.length() - 1; i >= 0; i--) {
            int add = min.charAt(i) + max.charAt(i + a) - 2 * '0';
            if (jw){
                add++;
                jw = false;
            }
            if (add >= 10){
                sb.append(add - 10);
                jw = true;
            }else {
                sb.append(add);
            }
        }
        for (int j = max.length() - min.length() - 1; j >= 0; j--) {
            int add = max.charAt(j) - '0';
            if (jw){
                add++;
                jw = false;
            }
            if (add >= 10){
                sb.append(add - 10);
                jw = true;
            }else {
                sb.append(add);
            }
        }
        if (jw){
            sb.append(1);
        }
        return sb.reverse().toString();
    }
}
