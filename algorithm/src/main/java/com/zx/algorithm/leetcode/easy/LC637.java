package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhouxin
 * @since 2020/4/2
 */
public class LC637 {
    public static void main(String[] args) {
        System.out.println(new LC637().averageOfLevels(new TreeNode(new Integer[]{2147483647,2147483647,2147483647})).toString().equals("[3, 14.5, 11]"));
        System.out.println(new LC637().averageOfLevels(new TreeNode(new Integer[]{3, 9, 20, null, null, 15, 7})).toString().equals("[3, 14.5, 11]"));
    }

    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        long sum = 0;
        int num = 0;
        int index = 1;
        List<Double> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            index--;
            if (poll != null) {
                sum += poll.val;
                num++;
                queue.add(poll.right);
                queue.add(poll.left);
            }
            if (index == 0 && num != 0){
                list.add(sum * 1.0 / num);
                sum = 0;
                num = 0;
                index = queue.size();
            }
        }
        return list;
    }
}