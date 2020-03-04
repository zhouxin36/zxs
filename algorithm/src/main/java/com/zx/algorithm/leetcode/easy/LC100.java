package com.zx.algorithm.leetcode.easy;

import java.util.Stack;

/**
 * @author zhouxin
 * @since 2020/3/3
 */
public class LC100 {

    public static void main(String[] args) {
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(p);
        stack2.push(q);
        while (!stack1.isEmpty()) {
            TreeNode p1 = stack1.pop();
            TreeNode p2 = stack2.pop();
            if (p1 == null && p2 == null) {
            } else if (p1 != null && p2 != null) {
                if (p1.val != p2.val) {
                    return false;
                }
                stack1.push(p1.left);
                stack1.push(p1.right);
                stack2.push(p2.left);
                stack2.push(p2.right);
            }else {
                return false;
            }
        }
        return true;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
