package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/28
 */
public class LC572 {

    public static void main(String[] args) {
        System.out.println(new LC572().isSubtree(new TreeNode(new Integer[]{3, 4, 5, 1, 2}), new TreeNode(new Integer[]{4, 1, 2})));
        System.out.println(!new LC572().isSubtree(new TreeNode(new Integer[]{3, 4, 5, 1, 2, null, null, null, null, 0}), new TreeNode(new Integer[]{4, 1, 2})));
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s, t);
    }

    public boolean equals(TreeNode x, TreeNode y) {
        if (x == null && y == null)
            return true;
        if (x == null || y == null)
            return false;
        return x.val == y.val && equals(x.left, y.left) && equals(x.right, y.right);
    }

    public boolean traverse(TreeNode s, TreeNode t) {
        return s != null && (equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
    }
}
