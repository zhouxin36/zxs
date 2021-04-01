package com.zx.jdkanalysis.performance.parallelism;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaxPayerBailoutDbImpl implements TaxPayerBailoutDB {
    private final Map<String,TaxPayerRecord> db;
    public TaxPayerBailoutDbImpl(int size) {
        db = new ConcurrentHashMap<String,TaxPayerRecord>(size);
    }

    @Override
    public TaxPayerRecord get(String id) {
        return db.get(id);
    }

    @Override
    public TaxPayerRecord add(String id, TaxPayerRecord record) {
        TaxPayerRecord old = db.put(id, record);
        if (old != null) {
            // restore old TaxPayerRecord
            old = db.put(id, old);
        }
        return old;
    }

    @Override
    public TaxPayerRecord remove(String id) {
        return db.remove(id);
    }

    @Override
    public int size() {
        return db.size();
    }
}