package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/23
 */
public class LC482 {

    public static void main(String[] args) {
        System.out.println(new LC482().licenseKeyFormatting("----", 1).equals(""));
        System.out.println(new LC482().licenseKeyFormatting("a-a-a-a-", 1).equals("A-A-A-A"));
        System.out.println(new LC482().licenseKeyFormatting("5F3Z-2e-9-w", 4).equals("5F3Z-2E9W"));
        System.out.println(new LC482().licenseKeyFormatting("2-5g-3-J", 2).equals("2-5G-3J"));
    }

    public String licenseKeyFormatting(String S, int K) {
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) != '-'){
                count++;
            }
        }
        int n = count % K;
        n = n == 0 ? K : n;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char charAt = S.charAt(i);
            if (charAt != '-'){
                if (charAt >= 'a' && charAt <= 'z'){
                    charAt -= 32;
                }
                sb.append(charAt);
                n--;
                if (n == 0){
                    n = K;
                    sb.append("-");
                }
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
