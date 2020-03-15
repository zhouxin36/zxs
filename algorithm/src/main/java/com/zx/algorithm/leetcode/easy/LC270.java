package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/15
 */
public class LC270 {

    public static void main(String[] args) {
        System.out.println(new LC270().closetValue(new TreeNode(new Integer[]{4,2,5,1,3}), 3.714) == 4);
        System.out.println(new LC270().closetValue(new TreeNode(new Integer[]{4,2,5,1,3}), 2.714) == 3);
    }

    public int closetValue(TreeNode root, double target) {
        int a = Integer.MAX_VALUE;
        double b = Double.MAX_VALUE;
        while (root != null){
            double abs = Math.abs(target - root.val);
            if (b > abs){
                b = abs;
                a = root.val;
            }
            if (root.val > target){
                root = root.left;
            }else if (root.val < target){
                root =root.right;
            }else {
                return root.val;
            }
        }
        return a;
    }

}
