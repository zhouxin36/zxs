package com.zx.algorithm.leetcode.easy;


/**
 * @author zhouxin
 * @since 2020/3/7
 */
public class LC155 {
    class MinStack {
        private final static int INIT_SIZE = 8;
        private int[] arr;
        private int length = 0;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            this.arr = new int[INIT_SIZE];
        }

        public void push(int x) {
            if (length >= arr.length) {
                reSize();
            }
            int min = length == 0 ? Integer.MAX_VALUE : arr[length - 1];
            arr[length] = x;
            length++;
            arr[length] = Math.min(x, min);
            length++;
        }

        private void reSize() {
            int[] newArr = new int[arr.length << 1];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            this.arr = newArr;
        }

        public void pop() {
            if (length == 0) {
                throw new RuntimeException("空栈");
            }
            length -= 2;
        }

        public int top() {
            if (length == 0) {
                throw new RuntimeException("空栈");
            }
            return arr[length - 2];
        }

        public int getMin() {
            if (length == 0) {
                throw new RuntimeException("空栈");
            }
            return arr[length - 1];
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
