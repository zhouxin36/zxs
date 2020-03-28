package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/28
 */
public class LC563 {

    public static void main(String[] args) {
        System.out.println(new LC563().findTilt(new TreeNode(new Integer[]{1, 2, 3})) == 1);
        System.out.println(new LC563().findTilt(new TreeNode(new Integer[]{1, 2, 3, 4, null, 5})) == 11);
    }

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lift = findTilt(root.left);
        int right = findTilt(root.right);
        int a = 0, b = 0;
        if (root.left != null) {
            a = root.left.val;
        }
        if (root.right != null) {
            b = root.right.val;
        }
        root.val += a + b;
        return lift + right + Math.abs(b - a);
    }
}
