package com.zx.algorithm.leetcode.easy;

import java.util.Stack;

/**
 * @author zhouxin
 * @since 2020/3/4
 */
@SuppressWarnings({"unused"})
public class LC101 {

    public boolean isSymmetric(TreeNode root) {
        Stack<TreeNode> stackLeft = new Stack<>();
        Stack<TreeNode> stackRight = new Stack<>();
        stackLeft.push(root);
        stackRight.push(root);
        while (!stackLeft.isEmpty() && !stackRight.isEmpty()) {
            TreeNode left = stackLeft.pop();
            TreeNode right = stackRight.pop();
            if (left == null && right == null){
            }else if (left != null && right != null) {
                if (left.val != right.val) {
                    return false;
                }
                stackLeft.push(left.left);
                stackLeft.push(left.right);
                stackRight.push(right.right);
                stackRight.push(right.left);
            }else {
                return false;
            }
        }
        if (stackLeft.isEmpty() && stackRight.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    private boolean go(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        return l.val == r.val && go(l.left, r.right) && go(l.right, r.left);
    }

    public boolean isSymmetric2(TreeNode root) {
        return root == null || go(root.left, root.right);
    }

}
