package com.zx.algorithm.leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zhouxin
 * @since 2020/3/11
 */
public class LC225 {
    class MyStack {

        Deque<Integer> deque;
        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            deque = new ArrayDeque<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            deque.push(x);
            for (int i = 0; i < deque.size() - 1; i++) {
                int pop = pop();
                deque.push(pop);
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return deque.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return deque.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return deque.isEmpty();
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}
