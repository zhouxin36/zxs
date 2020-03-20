package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/20
 */
public class LC405 {

    public static void main(String[] args) {
        System.out.println(new LC405().toHex(-2147483648).equals("80000000"));
        System.out.println(new LC405().toHex(111111).equals("1b207"));
        System.out.println(new LC405().toHex(26).equals("1a"));
        System.out.println(new LC405().toHex(-1).equals("ffffffff"));
    }

    public String toHex(int num) {
        int a = 0b1111;
        StringBuilder sb = new StringBuilder();
        int n = num & a;
        do{
            sb.append(toH(n));
            num >>>= 4;
            n = num & a;
        }while (num != 0);
        return sb.reverse().toString();
    }

    public char toH(int i){
        if (i >=0 && i <= 9){
            return (char)('0' + i);
        }else {
            return (char) ('a' + (i - 10));
        }
    }
}
