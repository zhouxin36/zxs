package com.zx.jdkanalysis.bean.mxbean;

import javax.management.MXBean;

/**
 * 注解MXBean 或者 *MXBean
 * @author zhouxin
 * @date 2019/1/25
 */
@MXBean
public interface QueueSamplerMXBean {
    public QueueSample getQueueSample();
    public void clearQueue();
}
