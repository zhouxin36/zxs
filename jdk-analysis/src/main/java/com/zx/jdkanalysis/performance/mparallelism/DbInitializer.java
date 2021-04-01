package com.zx.jdkanalysis.performance.mparallelism;

import java.util.List;
import java.util.concurrent.Callable;

public class DbInitializer implements Callable<DbInitializerFuture> {

    private TaxPayerBailoutDB db;
    private List<String>[] taxPayerList;
    private int recordsToCreate;

    public DbInitializer(TaxPayerBailoutDB db,
                         List<String>[] taxPayerList,
                         int recordsToCreate) {
        this.db = db;
        this.taxPayerList = taxPayerList;
        this.recordsToCreate = recordsToCreate;
    }

    @Override
    public DbInitializerFuture call() throws Exception {
        return BailoutMain.populateDatabase(db, taxPayerList,
                recordsToCreate);
    }
}