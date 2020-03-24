package com.zx.algorithm.leetcode.easy;

import java.util.*;

/**
 * @author zhouxin
 * @since 2020/3/24
 */
public class LC501 {

    private TreeNode treeNode;
    private int max = 0;
    private int count = 0;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC501().findMode(new TreeNode(new Integer[]{2147483647}))).equals(Arrays.toString(new int[]{2147483647})));
        System.out.println(Arrays.toString(new LC501().findMode(new TreeNode(new Integer[]{1, null, 2, 2}))).equals(Arrays.toString(new int[]{2})));
    }

    public int[] findMode(TreeNode root) {
        return findMode2(root);
    }

    public int[] findMode2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        treeNode = root;
        doFindMode2(root, list);
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public void doFindMode2(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        doFindMode2(root.left, list);
        count = treeNode.val == root.val ? count + 1 : 1;
        if(count == max) {
            list.add(root.val);
        }
        else if(count > max) {
            max = count;
            list.clear();
            list.add(root.val);
        }
        treeNode = root;
        doFindMode2(root.right, list);
    }

    public int[] findMode1(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        doFindMode1(root, map);
        Optional<Integer> max = map.values().stream().max(Integer::compareTo);
        if (max.isEmpty()) {
            return new int[]{};
        } else {
            Integer integer = max.get();
            Set<Integer> set = new HashSet<>();
            map.forEach((x, y) -> {
                if (y.equals(integer)) {
                    set.add(x);
                }
            });
            int[] arr = new int[set.size()];
            Iterator<Integer> iterator = set.iterator();
            for (int i = 0; iterator.hasNext(); i++) {
                arr[i] = iterator.next();
            }

            return arr;
        }

    }

    public void doFindMode1(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return;
        }
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        doFindMode1(root.left, map);
        doFindMode1(root.right, map);
    }
}
