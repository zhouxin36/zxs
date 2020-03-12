package com.zx.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @since 2020/3/12
 */
public class LC234 {
    public static void main(String[] args) {
        System.out.println(!new LC234().isPalindrome(new ListNode(new int[]{1, 2})));
        System.out.println(new LC234().isPalindrome(new ListNode(new int[]{1, 2, 2, 1})));
    }

    public boolean isPalindrome2(ListNode head) {
        List<ListNode> listNodes = new ArrayList<>();
        while (head != null) {
            listNodes.add(head);
            head = head.next;
        }
        int index = 0;
        int size = listNodes.size() - 1;
        while (index <= (size >> 1)) {
            if (listNodes.get(index).val != listNodes.get(size - index).val) {
                return false;
            }
            index++;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        int length = 0;
        ListNode a = head;
        while (a != null) {
            length++;
            a = a.next;
        }
        int mid = length % 2 == 0 ? (length >> 1) - 1 : (length >> 1);
        a = head;
        while (mid != 0) {
            a = a.next;
            mid--;
        }
        ListNode h = a.next, p = null;
        while (h != null) {
            ListNode tmp = h.next;
            h.next = p;
            p = h;
            h = tmp;
        }
        a.next = p;
        a = a.next;
        while (a != null) {
            if (head.val != a.val) {
                return false;
            }
            head = head.next;
            a = a.next;
        }
        // 翻转
        return true;
    }

}
