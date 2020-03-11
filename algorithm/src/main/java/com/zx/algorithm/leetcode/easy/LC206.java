package com.zx.algorithm.leetcode.easy;

import java.util.Stack;

/**
 * @author zhouxin
 * @since 2020/3/11
 */
public class LC206 {
    public static void main(String[] args) {
        System.out.println(new LC206().reverseList(new ListNode(new int[]{1, 2, 3, 4, 5})).toString().equals("5,4,3,2,1,"));
    }

    public ListNode reverseList(ListNode head) {
        return reverseList2(head);
    }


    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            ListNode next = head.next;
            head.next = null;
            head = next;
        }
        ListNode h = stack.pop();
        ListNode he = h;
        while (!stack.isEmpty()) {
            h.next = stack.pop();
            h = h.next;
        }
        return he;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode next = null;

        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public ListNode reverseList3(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
