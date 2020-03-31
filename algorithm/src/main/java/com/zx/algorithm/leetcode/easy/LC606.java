package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/31
 */
public class LC606 {

    public static void main(String[] args) {
        System.out.println(new LC606().tree2str(new TreeNode(new Integer[]{1, 2, 3, 4})).equals("1(2(4))(3)"));
        System.out.println(new LC606().tree2str(new TreeNode(new Integer[]{1, 2, 3, null, 4})).equals("1(2()(4))(3)"));
    }

    public String tree2str(TreeNode t) {
        return tree2str1(t);
    }

    public String tree2str1(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        doTree2str1(t, sb);
        return sb.toString();
    }

    private void doTree2str1(TreeNode t, StringBuilder sb) {
        if (t == null) {
            return;
        }
        sb.append(t.val);
        if (t.left == null && t.right == null){
            return;
        }
        sb.append("(");
        doTree2str1(t.left, sb);
        sb.append(")");
        if (t.right == null){
            return;
        }
        sb.append("(");
        doTree2str1(t.right, sb);
        sb.append(")");
    }
}
