package com.zx.algorithm.leetcode;

import java.util.Stack;

/**
 * 给定一个编码字符串 S。为了找出解码字符串并将其写入磁带，从编码字符串中每次读取一个字符，并采取以下步骤：
 * <p>
 * 如果所读的字符是字母，则将该字母写在磁带上。
 * 如果所读的字符是数字（例如 d），则整个当前磁带总共会被重复写 d-1 次。
 * 现在，对于给定的编码字符串 S 和索引 K，查找并返回解码字符串中的第 K 个字母。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "leet2code3", K = 10
 * 输出："o"
 * 解释：
 * 解码后的字符串为 "leetleetcodeleetleetcodeleetleetcode"。
 * 字符串中的第 10 个字母是 "o"。
 * <p>
 *
 * @author zhouxin
 * @since 2019/6/12
 */
public class DecodeAtIndex {
    public String decodeAtIndex(String S, int K) {
        long size = 0;
        int N = S.length();
        for (int i = 0; i < N; ++i) {
            char charAt = S.charAt(i);
            if (charAt >= '2' && charAt <= '9')
                size *= S.charAt(i) - '0';
            else
                size++;
        }

        for (int i = N-1; i >=0; --i) {
            K %= size;
            char charAt = S.charAt(i);
            if (K == 0 && charAt >= 'a' && charAt <= 'z')
                return "" + charAt;

            if (charAt >= '2' && charAt <= '9')
                size /= charAt - '0';
            else
                size--;
        }
        return "";
    }


    public String toString(String str) {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '2' && charAt <= '9') {
                st.append(st.toString().repeat(Math.max(0, charAt - '1')));
            } else {
                st.append(charAt);
            }
        }
        return st.toString();
    }

    public String decodeAtIndex2(String S, int K) {
        return S.charAt(K - 1) + "";
    }

    public static void main(String[] args) {
        String str = "ab2cz34ez5";
        DecodeAtIndex decodeAtIndex = new DecodeAtIndex();
        System.out.println(decodeAtIndex.toString(str));
//        System.out.print(decodeAtIndex.decodeAtIndex("a23", 6));
        for (int i = 1; i <= 200; i++) {
            System.out.print(decodeAtIndex.decodeAtIndex(str, i));
        }
    }
}
