package com.zx.algorithm.queue;

/**
 * @author zhouxin
 * @since 2019/6/8
 */
public class HeapQueue<T extends Comparable<T>> {

    private T[] value;

    private int size;

    public HeapQueue(int initNumber){
        //noinspection unchecked
        value = (T[])new Comparable[initNumber];
    }

    public void insert(T v){
        size++;
        if(size > value.length){
            reSize();
        }
        value[size - 1] = v;
        swim();
    }

    private void reSize() {
        int i = value.length << 1;
        T[] oldArray = value;
        //noinspection unchecked
        value = (T[])new Comparable[i];
        for (int j = 0; j < oldArray.length; j++) {
            value[j] = oldArray[j];
        }
    }

    private void swim() {
        int k = size - 1;
        while (k > 0 && value[k].compareTo(value[(k - 1) / 2]) < 0){
            swap(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    public T deleteMin(){
        T t = value[0];
        value[0] = value[size - 1];
        size--;
        sink();
        return t;
    }

    private void sink() {
        int k = 0;
        while (2 * k < size){
            int min = k;
            int i = 2 * k + 1;
            int j = 2 * k + 2;
            if(i < size && value[min].compareTo(value[i]) > 0){
                min = i;
            }
            if(j < size && value[min].compareTo(value[j]) > 0){
                min = j;
            }
            if(min == k){
                break;
            }
            swap(k, min);
            k = min;
        }
    }

    public void swap(int a, int b){
        T t = value[a];
        value[a] = value[b];
        value[b] = t;
    }

    public int getSize() {
        return size;
    }


    public static void main(String[] args) {
        Integer i = Integer.valueOf("-91283472332");
        int a = ~1;
        System.out.println(i);
    }
}
