package com.zx.algorithm.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhouxin
 * @since 2020/3/17
 */
public class LC346 {

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1) == 1);
        System.out.println(movingAverage.next(10) == 5.5);
        System.out.println(movingAverage.next(3) == 14.0 / 3);
        System.out.println(movingAverage.next(5) == 6);
    }

    public static class MovingAverage {
        private Queue<Integer> queue;
        private int size = 0;
        private int largeSize;

        public MovingAverage(int size) {
            this.queue = new LinkedList<>();
            this.largeSize = size;
        }

        public double next(int val) {
            if (size == largeSize) {
                queue.poll();
            } else {
                size++;
            }
            queue.add(val);
            int sum = 0;
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                sum += poll;
                queue.add(poll);
            }
            return sum * 1.0 / size;
        }
    }

}
