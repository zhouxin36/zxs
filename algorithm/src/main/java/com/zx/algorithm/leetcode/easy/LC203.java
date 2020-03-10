package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/10
 */
public class LC203 {

    public static void main(String[] args) {
        System.out.println(new LC203().removeElements(new ListNode(new int[]{1, 2, 6, 3, 4, 5, 6}), 6).toString().equals("1,2,3,4,5,"));
        System.out.println(new LC203().removeElements(new ListNode(new int[]{6, 6, 6, 3, 4, 5, 6}), 6).toString().equals("3,4,5,"));
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode pref = head;
        ListNode node = head;
        while (node != null){
            if (node.val == val){
                if (node == head){
                    node = pref = head = head.next;
                }else {
                    pref.next = node.next;
                    node = node.next;
                }
            }else {
                pref = node;
                node = node.next;
            }
        }
        return head;
    }

}
