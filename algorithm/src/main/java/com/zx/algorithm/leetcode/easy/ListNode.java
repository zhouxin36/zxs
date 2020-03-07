package com.zx.algorithm.leetcode.easy;

/**
 * @author zhouxin
 * @since 2020/3/7
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
        } else if (arr.length == 1) {
            this.val = arr[0];
        } else {
            ListNode pr = this;
            for (int i = 1; i < arr.length; i++) {
                pr.next = new ListNode(arr[i]);
                pr = pr.next;
            }
        }
    }

    /**
     * 尾节点链接第pos节点
     */
    ListNode(int[] arr, int pos) {
        ListNode listNode = null;
        if (arr != null && arr.length != 0)  {
            this.val = arr[0];
            if (pos == 0){
                listNode = this;
            }
            if (arr.length == 1){
                if (pos == 0){
                    this.next = this;
                }
                return;
            }
            ListNode pr = this;
            for (int i = 1; i < arr.length; i++) {
                pr.next = new ListNode(arr[i]);
                pr = pr.next;
                if (pos == i){
                    listNode = pr;
                }
            }
            pr.next = listNode;
        }

    }

}
