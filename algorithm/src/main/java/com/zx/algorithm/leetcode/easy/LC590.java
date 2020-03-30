package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/3/30
 */
public class LC590 {
    public static void main(String[] args) {
        System.out.println(new LC590().postorder(Node.getInstance(new Integer[]{1, null, 3, 2, 4, null, 5, 6})).toString().replace(" ", "").equals("[5,6,3,2,4,1]"));
        System.out.println(new LC590().postorder(Node.getInstance(new Integer[]{1, null, 2, 3, 4, 5, null, null, 6, 7, null, 8, null, 9, 10, null, null, 11, null, 12, null, 13, null, null, 14})).toString().replace(" ", "").equals("[2,6,14,11,7,3,12,8,4,13,9,10,5,1]"));
    }

    public List<Integer> postorder(Node root) {
        return postorder1(root, new ArrayList<>());
    }

    public List<Integer> postorder1(Node root, List<Integer> list) {
        if (root == null) {
            return list;
        }
        if (root.children != null && root.children.size() != 0) {
            root.children.forEach(e -> postorder1(e, list));
        }
        list.add(root.val);
        return list;
    }
}
