package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/27
 */
public class LC543 {
    int ans;

    public static void main(String[] args) {
        System.out.println(new LC543().diameterOfBinaryTree(new TreeNode(new Integer[]{1, 2, 3, 4, 5})) == 3);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) return 0;
        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans, L + R + 1);
        return Math.max(L, R) + 1;
    }
}
