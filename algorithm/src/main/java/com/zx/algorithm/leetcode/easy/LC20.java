package com.zx.algorithm.leetcode.easy;

import java.util.Stack;

/**
 * @author zhouxin
 * @since 2020/3/1
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC20 {

    public static void main(String[] args) {
        System.out.println(!new LC20().isValid("{{)}"));
        System.out.println(new LC20().isValid("()"));
        System.out.println(new LC20().isValid("()[]{}"));
        System.out.println(!new LC20().isValid("(]"));
        System.out.println(!new LC20().isValid("([)]"));
        System.out.println(new LC20().isValid("{[]}"));
    }

    public boolean isValid(String s) {
        return isValid1(s);
    }

    public boolean isValid1(String s) {
        if (null == s || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '(':
                case '[':
                case '{':
                    stack.push(ch);
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.isEmpty() || ch - stack.peek() > 2 || ch - stack.peek() < -2) {
                        return false;
                    }
                    stack.pop();
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
