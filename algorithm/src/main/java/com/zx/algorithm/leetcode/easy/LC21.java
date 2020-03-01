package com.zx.algorithm.leetcode.easy;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 *
 * @author zhouxin
 * @since 2020/3/1
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC21 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(1);
        l1.next(2).next(4);
        l2.next(3).next(4);
        l3.next(1).next(2).next(3).next(4).next(4);
        System.out.println(new LC21().mergeTwoLists(l1, l2).toString().equals(l3
                .toString()));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return mergeTwoLists1(l1, l2);
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode result = new ListNode(0);
        ListNode next = result;
        do {
            if (l1 == null) {
                next = next.next = new ListNode(l2.val);
                l2 = l2.next;
            } else if (l2 == null) {
                next = next.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                if (l1.val >= l2.val) {
                    next = next.next = new ListNode(l2.val);
                    l2 = l2.next;
                } else {
                    next = next.next = new ListNode(l1.val);
                    l1 = l1.next;
                }
            }
        } while (l1 != null || l2 != null);
        return result.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode next(int val) {
            this.next = new ListNode(val);
            return next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode next = this;
            while (true) {
                sb.append(next.val);
                if (next.next != null) {
                    next = next.next;
                } else {
                    return sb.toString();
                }
            }
        }
    }
}
