package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/4/3
 */
public class LC653 {

    public static void main(String[] args) {
        System.out.println(new LC653().findTarget(new TreeNode(new Integer[]{1, 0, 4, -2, null, 3}), 7));
        System.out.println(!new LC653().findTarget(new TreeNode(new Integer[]{2, null, 3}), 6));
        System.out.println(new LC653().findTarget(new TreeNode(new Integer[]{5, 3, 6, 2, 4, null, 7}), 9));
        System.out.println(!new LC653().findTarget(new TreeNode(new Integer[]{5, 3, 6, 2, 4, null, 7}), 28));
    }

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = findTarget1(root, new ArrayList<>());
        int head = 0;
        int tag = list.size() - 1;
        while (head < tag) {
            if (list.get(head) + list.get(tag) > k) {
                tag--;
            } else if (list.get(head) + list.get(tag) < k) {
                head++;
            } else {
                return true;
            }
        }
        return false;
    }

    private List<Integer> findTarget1(TreeNode root, List<Integer> list) {
        if (root == null){
            return list;
        }
        findTarget1(root.left,list);
        list.add(root.val);
        findTarget1(root.right, list);
        return list;
    }

}
