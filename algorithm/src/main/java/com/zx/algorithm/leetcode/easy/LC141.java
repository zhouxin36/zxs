package com.zx.algorithm.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhouxin
 * @since 2020/3/7
 */
public class LC141 {

    public static void main(String[] args) {
        System.out.println(new LC141().hasCycle(new ListNode(new int[]{3, 2, 0, -4}, 1)));
        System.out.println(new LC141().hasCycle(new ListNode(new int[]{1, 2}, 0)));
        System.out.println(!new LC141().hasCycle(new ListNode(new int[]{1}, -1)));
    }

    public boolean hasCycle(ListNode head) {
        return method2(head);
    }

    private boolean method1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        for (; ; head = head.next) {
            if (head == null) {
                return false;
            }
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
            }
        }
    }

    private boolean method2(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode a = head;
        ListNode b = head;
        do {
            if (a.next == null || b.next == null || b.next.next == null) {
                return false;
            }
            a = a.next;
            b = b.next.next;
        } while (a != b);
        return true;
    }
}
