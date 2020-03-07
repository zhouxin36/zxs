package com.zx.algorithm.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhouxin
 * @since 2020/3/7
 */
public class LC160 {

    public static void main(String[] args) {
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        while (headA != null || headB != null) {
            if (headA != null) {
                if (set.contains(headA)) {
                    return headA;
                } else {
                    set.add(headA);
                    headA = headA.next;
                }
            }
            if (headB != null) {
                if (set.contains(headB)) {
                    return headB;
                } else {
                    set.add(headB);
                    headB = headB.next;
                }
            }
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int a = 0;
        int b = 0;
        ListNode ta = headA;
        ListNode tb = headB;
        while (ta != null) {
            a++;
            ta = ta.next;
        }
        while (tb != null) {
            b++;
            tb = tb.next;
        }
        ListNode min;
        ListNode max;
        if (a > b) {
            min = headB;
            max = headA;
            while (a != b){
                max = max.next;
                a--;
            }
        }else {
            min = headA;
            max = headB;
            while (a != b){
                max = max.next;
                b--;
            }
        }
        while (max != null){
            if (max == min){
                return max;
            }
            max = max.next;
            min = min.next;
        }
        return null;
    }
}
