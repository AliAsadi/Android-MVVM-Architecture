package com.aliasadi.mvvm.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Ali Asadi on 31/07/2019.
 */
public class DiskExecutor implements Executor {

    private Executor diskExecutor;

    public DiskExecutor() {
        this.diskExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(Runnable runnable) {
        diskExecutor.execute(runnable);
    }
}
