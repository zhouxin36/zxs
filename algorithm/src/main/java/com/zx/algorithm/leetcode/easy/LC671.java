package com.zx.algorithm.leetcode.easy;

import java.util.*;

/**
 * @author zhouxin
 * @since 2020/4/3
 */
public class LC671 {

    public static void main(String[] args) {
        System.out.println(new LC671().findSecondMinimumValue(new TreeNode(new Integer[]{2, 2, 5, null, null, 5, 7})) == 5);
        System.out.println(new LC671().findSecondMinimumValue(new TreeNode(new Integer[]{2, 2, 2})) == -1);
    }

    public int findSecondMinimumValue(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int size = 1;
        queue.add(root);
        int num = -1;
        while (!queue.isEmpty()){
            size--;
            TreeNode pop = queue.poll();
            set.add(pop.val);
            if (pop.left != null){
                queue.add(pop.left);
            }
            if (pop.right != null){
                queue.add(pop.right);
            }
            if (size == 0){
                if (set.size() >= 2){
                    Integer[] integers = set.toArray(new Integer[]{});
                    Arrays.sort(integers);
                    num = integers[integers.length - 2];
                }
                size = queue.size();
                set.clear();
            }
        }
        return num;
    }
}
