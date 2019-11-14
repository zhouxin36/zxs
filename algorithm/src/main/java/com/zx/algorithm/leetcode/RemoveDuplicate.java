package com.zx.algorithm.leetcode;

import edu.princeton.cs.algs4.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 给定一个仅包含小写字母的字符串，去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 输入: "bcabc"
 * 输出: "abc"
 * <p>
 * 输入: "cbacdcbc"
 * 输出: "acdb"
 *
 * @author zhouxin
 * @since 2019/6/5
 */
public class RemoveDuplicate {

    private final static Logger logger = LoggerFactory.getLogger(RemoveDuplicate.class);

    private final static int arraySize = 26;

    private final static char starChar = 'a';

    public String removeDuplicateLetters(String s) {
        int[] keys = new int[arraySize];
        boolean[] visited = new boolean[arraySize];
        char[] sChars = s.toCharArray();
        int index = 0;
        for(int i=0;i<s.length();i++){
            if(keys[sChars[i] - starChar] == 0){
                index++;
            }
            keys[sChars[i] - starChar]++;
        }
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < sChars.length; i++){
            char c = sChars[i];
            while(!stack.isEmpty() && c < stack.peek() && keys[stack.peek() - starChar] > 1
                    && !visited[c - starChar]){
                char ch = stack.pop();
                visited[ch - starChar] = false;
                keys[ch - starChar]--;
            }
            if(visited[c - starChar]){
                keys[c - starChar]--;
                continue;
            }
            stack.push(c);
            visited[c - starChar] = true;
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDuplicate removeDuplicate = new RemoveDuplicate();
        String str = "zbazbaabzbazba";
        String str2 = "bcabc";
        logger.info("str:{}.removeDuplicate:{}", str, removeDuplicate.removeDuplicateLetters(str));
        logger.info("str2:{}.removeDuplicate:{}", str2, removeDuplicate.removeDuplicateLetters(str2));
    }
}
