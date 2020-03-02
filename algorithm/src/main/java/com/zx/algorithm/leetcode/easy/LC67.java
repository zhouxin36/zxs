package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/2
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC67 {

    public static void main(String[] args) {
        System.out.println(new LC67().addBinary("11", "1").equals("100"));
        System.out.println(new LC67().addBinary("1010", "1011").equals("10101"));
    }

    public String addBinary(String a, String b) {
        return addBinary1(a, b);
    }

    public String addBinary1(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int index = 1; a.length() >= index || b.length() >= index; index++) {
            int f1 = a.length() >= index ? a.charAt(a.length() - index) - '0' : 0;
            int f2 = b.length() >= index ? b.charAt(b.length() - index) - '0' : 0;
            int num = f1 + f2 + carry;
            switch (num) {
                case 0:
                case 1:
                    sb.append(num);
                    carry = 0;
                    break;
                case 2:
                case 3:
                    sb.append(num - 2);
                    carry = 1;
                    break;
                default:
                    throw new IllegalArgumentException("非法输入");
            }
        }
        String str = sb.reverse().toString();
        if (carry == 1) {
            return 1 + str;
        }
        return str;
    }
}
