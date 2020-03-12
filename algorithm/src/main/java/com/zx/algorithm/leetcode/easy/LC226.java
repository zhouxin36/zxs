package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/12
 */
public class LC226 {

    public static void main(String[] args) {
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
