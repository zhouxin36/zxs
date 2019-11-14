package com.zx.algorithm.leetcode;

import edu.princeton.cs.algs4.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用两个栈实现队列
 *
 * @author zhouxin
 * @since 2019/6/5
 */
public class StackToMyQueue {

    private final static Logger logger = LoggerFactory.getLogger(StackToMyQueue.class);
    private Stack<Integer> input;
    private Stack<Integer> output;

    /**
     * Initialize your data structure here.
     */
    public StackToMyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if (!output.isEmpty()) {
            swapStack(output, input);
        }
        input.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (!input.isEmpty()) {
            swapStack(input, output);
        }
        return output.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (!input.isEmpty()) {
            swapStack(input, output);
        }
        return output.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

    public void swapStack(Stack<Integer> in, Stack<Integer> out) {
        int size = in.size();
        for (int i = 0; i < size; i++) {
            out.push(in.pop());
        }
    }

    public static void main(String[] args) {
        StackToMyQueue myQueue = new StackToMyQueue();
        myQueue.push(1);
        myQueue.push(2);
        logger.info("peek:{},pop:{},empty:{}", myQueue.peek(), myQueue.pop(), myQueue.empty());
    }

}
