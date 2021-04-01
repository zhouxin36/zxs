package com.zx.jdkanalysis.performance.resize;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TaxPayerBailoutDbImpl implements TaxPayerBailoutDB {
    private final Map<String, Map<String,TaxPayerRecord>> db;

    public TaxPayerBailoutDbImpl(int dbSize, int numberOfStates) {
        db = new HashMap<String,Map<String,TaxPayerRecord>>(dbSize);
        for (int i = 0; i < numberOfStates; i++) {
            Map<String,TaxPayerRecord> map =
                    Collections.synchronizedMap(
                            new HashMap<String,TaxPayerRecord>(
                                    dbSize/numberOfStates));
            db.put(BailoutMain.states[i], map);
        }
    }

    @Override
    public TaxPayerRecord get(String id, String state) {
        Map<String,TaxPayerRecord> map = getStateMap(state);
        if (map == null) {
            System.out.println("Unable to find state: " + state);
        }
        return map.get(id);
    }

    @Override
    public TaxPayerRecord add(String id, TaxPayerRecord record) {
        Map<String,TaxPayerRecord> map = getStateMap(record.getState());
        // Update tax payer's record if found
        TaxPayerRecord old = map.put(id, record);
        if (old != null) {
            // not found, restore old TaxPayerRecord
            old = map.put(id, old);
        }
        return old;
    }

    @Override
    public TaxPayerRecord remove(String id, String state) {
        Map<String,TaxPayerRecord> map = getStateMap(state);
        TaxPayerRecord tmpRecord = null;
        if (map != null)
            tmpRecord = map.remove(id);
        return tmpRecord;
    }

    @Override
    public int size() {
        int size = 0;
        Iterator<Map<String,TaxPayerRecord>> itr =
                db.values().iterator();
        while (itr.hasNext()) {
            Map<String,TaxPayerRecord> m = itr.next();
            if (m != null)
                size += m.size();
        }
        return size;
    }

    private Map<String, TaxPayerRecord> getStateMap(String state) {
        Map<String,TaxPayerRecord> map = db.get(state);
        if (map == null) {
            throw new UnsupportedOperationException(
                    "State (" + state + ") " +
                            "not found in tax payer database.");
        }
        return map;
    }
}