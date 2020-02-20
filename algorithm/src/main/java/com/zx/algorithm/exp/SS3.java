package com.zx.algorithm.exp;

import java.util.*;

/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * * 输入: S = "a1b2c"
 * 输出: ["a1b2c", "a1b2C", "a1B2c", "a1B2C", "A1b2c", "A1b2C", "A1B2c", "A1B2C"]
 * <p>
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 * <p>
 * 输入: S = "12345"
 * 输出: ["12345"]
 * 注意：
 * <p>
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 *
 * @author zhouxin
 * @since 2020/2/9
 */
public class SS3 {

    public static void main(String[] args) {
        String str = "3z4";
        System.out.println(method(str));
    }

    private static List<String> method(String str) {
        if (str.length() > 12) {
            throw new IllegalArgumentException("字符串长度不超过12");
        }
        int n = 0;
        List<Integer> locations = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
                // 计算字母个数，保存字母位置
                n++;
                locations.add(i);
            } else if (ch < '0' || ch > '9') {
                throw new IllegalArgumentException("字符仅由数字和字母组成");
            }
        }
        if (locations.isEmpty()) {
            return Collections.singletonList(str);
        }
        // 字符串个数
        int size = 2 << (n - 1);
        List<String> result = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0, k = 0; j < str.length(); j++) {
                int location = n > k ? locations.get(k) : -1;
                if (j == location) {
                    // 计算当前字母大小
                    sb.append(culStr(str.charAt(j), culBinary(i - 1, k)));
                    k++;
                } else {
                    sb.append(str.charAt(j));
                }
            }
            result.add(sb.toString());
        }
        return result;
    }

    /**
     * 计算二进制num 第digit是否为1
     */
    private static boolean culBinary(int num, int digit) {
        return (num & (1 << digit)) != 0;
    }

    /**
     * 计算当前字符是否更改，1为更改，0为不更改
     */
    private static char culStr(char ch, boolean isOne) {
        if (!isOne) {
            return ch;
        }
        if (ch >= 'Z') {
            return (char) (ch - 32);
        } else {
            return (char) (ch + 32);
        }
    }
}
