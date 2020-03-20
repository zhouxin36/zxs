package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/20
 */
public class LC404 {

    public static void main(String[] args) {
        System.out.println(new LC404().sumOfLeftLeaves(new TreeNode(new Integer[]{3, 9, 20, null, null, 15, 7})));
        System.out.println(new LC404().sumOfLeftLeaves(new TreeNode(new Integer[]{3, 9, 20, null, null, 15, 7})) == 24);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        return doSumOfLeftLeaves(root, false);
    }

    public int doSumOfLeftLeaves(TreeNode root, boolean isLeft) {
        if (root == null){
            return 0;
        }
        if (isLeft && root.right == null && root.left == null){
            return root.val;
        }
        return doSumOfLeftLeaves(root.right, false) + doSumOfLeftLeaves(root.left, true);
    }
}
