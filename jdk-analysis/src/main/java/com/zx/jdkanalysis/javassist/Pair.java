package com.zx.jdkanalysis.javassist;

/**
 * @author zhouxin
 * @since 2019/7/8
 */
/**
 * Created by fengyiwei on 2017/11/28.
 */
public class Pair<K, V> {
    private K first;
    private V second;

    public Pair() {
    }

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return this.first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return this.second;
    }

    public void setSecond(V second) {
        this.second = second;
    }
}