package com.zx.jdkanalysis.performance.mparallelism;

public class DbInitializerFuture {
    private int recordsCreated;

    public DbInitializerFuture() {}

    public void addToRecordsCreated(int value) {
        recordsCreated += value;
    }

    public int getRecordsCreated() {
        return recordsCreated;
    }
}