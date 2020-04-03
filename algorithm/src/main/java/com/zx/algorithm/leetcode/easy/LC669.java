package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/4/3
 */
public class LC669 {

    public static void main(String[] args) {
        System.out.println(new LC669().trimBST(new TreeNode(new Integer[]{3, 0, 4, null, 2, null, null, 1}), 1, 3));
        System.out.println(new LC669().trimBST(new TreeNode(new Integer[]{1, 0, 2}), 1, 2));
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val >= L && root.val <= R) {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        } else if (root.val > R) {
            return trimBST(root.left, L, R);
        } else {
            return trimBST(root.right, L, R);
        }
    }
}
