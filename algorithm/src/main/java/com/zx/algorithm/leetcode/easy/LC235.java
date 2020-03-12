package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/12
 */
public class LC235 {

    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode node1 = lowestCommonAncestor(root.left, p, q);
        TreeNode node2 = lowestCommonAncestor(root.right, p, q);
        if (node1 != null && node2 != null) {
            return root;
        } else if (node1 != null) {
            return node1;
        } else if (node2 != null) {
            return node2;
        } else {
            return null;
        }
    }
}
