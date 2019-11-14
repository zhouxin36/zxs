package com.zx.jdkanalysis.bean.mxbean;

import java.beans.ConstructorProperties;
import java.util.Date;

/**
 * @author zhouxin
 * @date 2019/1/25
 */
public class QueueSample {

    private final Date date;
    private final int size;
    private final String head;

    @ConstructorProperties({"date", "size", "head"})
    public QueueSample(Date date, int size,
                       String head) {
        this.date = date;
        this.size = size;
        this.head = head;
    }

    public Date getDate() {
        return date;
    }

    public int getSize() {
        return size;
    }

    public String getHead() {
        return head;
    }
}
