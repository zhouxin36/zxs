package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/3
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LC83 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next(2).next(2);
        System.out.println(new LC83().deleteDuplicates(l1).toString().equals("12"));
    }

    public ListNode deleteDuplicates(ListNode head) {
        return deleteDuplicates1(head);
    }

    public ListNode deleteDuplicates1(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode tmp = head;
        ListNode node = head.next;
        while (node != null){
            if(tmp.val == node.val){
                tmp.next = node.next;
            }else {
                tmp = node;
            }
            node = node.next;
        }
        return head;
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
