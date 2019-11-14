package com.zx.jdkanalysis.bean.mxbean;

import java.util.Date;
import java.util.Queue;

/**
 * @author zhouxin
 * @date 2019/1/25
 */
public class QueueSampler implements QueueSamplerMXBean {

    private final Queue<String> queue;

    public QueueSampler (Queue<String> queue) {
        this.queue = queue;
    }

    public QueueSample getQueueSample() {
        synchronized (queue) {
            return new QueueSample(new Date(),
                    queue.size(), queue.peek());
        }
    }

    public void clearQueue() {
        synchronized (queue) {
            queue.clear();
        }
    }

}
