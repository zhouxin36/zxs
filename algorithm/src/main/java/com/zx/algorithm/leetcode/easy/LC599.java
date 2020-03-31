package com.zx.algorithm.leetcode.easy;

import java.util.*;

/**
 * @author zhouxin
 * @since 2020/3/31
 */
public class LC599 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC599().findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"}, new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"})).equals("[Shogun]"));
        System.out.println(Arrays.toString(new LC599().findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"}, new String[]{"KFC", "Shogun", "Burger King"})).equals("[Shogun]"));
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        List<String> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (min < i) {
                break;
            }
            Integer index = map.get(list2[i]);
            if (index == null) {
                continue;
            }
            index += i;
            if (min == index) {
                list.add(list2[i]);
            } else if (min > index) {
                min = index;
                list.clear();
                list.add(list2[i]);
            }
        }
        return list.toArray(new String[]{});
    }
}
