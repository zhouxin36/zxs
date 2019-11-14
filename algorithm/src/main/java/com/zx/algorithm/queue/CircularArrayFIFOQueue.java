package com.zx.algorithm.queue;

/**
 * FIFO循环队列
 *
 * @author zhouxin
 * @since 2019/6/21
 */
public class CircularArrayFIFOQueue implements Queue {

    /**
     * 环形数组大小上限
     */
    private static final int MAX_ARRAY_SIZE = 65536;

    /**
     * 尾节点与头节点至少留有的空间
     */
    private static final int SPACE_SIZE = 1;

    /**
     * 取模数/数组大小
     */
    private int modNumber;

    /**
     * 环形数组，存放队列元素
     */
    private Object[] items;

    /**
     * 队列头部（在items中）的下标
     */
    private int front;

    /**
     * 队列尾部（在items中）的下标
     */
    private int rear;

    @Override
    public boolean enqueue(Object o) {
        if (isFull()) {
            return false;
        }
        if (o == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        //入队列下标为当前尾下标 + 1
        int item = (rear + 1 + modNumber) % modNumber;
        items[item] = o;
        rear = (rear + 1) % modNumber;
        return true;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }
        Object result = items[front];
        items[front] = null;
        front = (front + 1) % modNumber;
        return result;
    }

    @Override
    public int size() {
        return (rear - front + 1 + modNumber) % modNumber;
    }

    public boolean isEmpty() {
        return (rear + 1) % modNumber == front;
    }

    public boolean isFull() {
        return (rear + 2) % modNumber == front;
    }

    /**
     * 带参数的构造函数，输入参数为队列初始化的大小。
     * 此处约定队列长度不得超过初始化的长度。
     * 初始化时，rear应指向front的“前”一元素。
     *
     * @param initQueueSize 队列中最多可容纳的元素数量
     */
    public CircularArrayFIFOQueue(int initQueueSize) {
        if (initQueueSize < 1) {
            throw new IllegalArgumentException(
                    "初始化容量不能小于1");
        }
        this.modNumber = initQueueSize + SPACE_SIZE;
        if (modNumber > MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(
                    "初始化容量超过上限，初始化长度：" +
                    initQueueSize + "，约定最大容量：" +
                            (MAX_ARRAY_SIZE - SPACE_SIZE));
        }
        this.items = new Object[modNumber];
        this.front = 0;
        this.rear = modNumber - 1;
    }
}
