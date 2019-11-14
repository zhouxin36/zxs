package com.zx.algorithm.queue;

/**
 * @author zhouxin
 * @since 2019/6/21
 */
public interface Queue {

    /**
     * 入队操作：将元素加入队列尾部，队列尾指针（rear）后移
     *
     * @param o 待加入队列的元素
     * @return 入队成功时返回true， 否则返回false
     */
    boolean enqueue(Object o);

    /**
     * 出队操作：返回队列头部元素，队列头指针（front）后移
     * @return 返回队列头部元素
     */
    Object dequeue();

    /**
     * 队列长度
     *
     * @return 队列中元素的个数
     */
    int size();
}
