package io.github.support.lock;

import io.github.support.function.DoFunction;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

public abstract class AbstractLock implements EnhanceLock {

    @Override
    public void lock() {
        doLock(0, null);
    }

    @Override
    public boolean tryLock() {
        return doLock(0, null);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return doLock(time, unit);
    }

    @Override
    public void unlock() {
        doUnLock();
    }

    @Override
    public void ifLockFail(DoFunction doFunction) {
        if (!tryLock()) {
            doFunction.doIt();
        }
    }

    @Override
    public void ifLockFail(long time, TimeUnit timeUnit, DoFunction doFunction) {
        boolean suc = false;
        try {
            suc = tryLock(time, timeUnit);
        } catch (InterruptedException e) {
            //nothing
        }
        if (!suc) {
            doFunction.doIt();
        }
    }

    @Override
    public boolean ifAllowLock(BooleanSupplier supplier) {
        return allowLock(supplier);
    }

    protected abstract boolean allowLock(BooleanSupplier booleanSupplier);

    protected abstract boolean doLock(long time, TimeUnit timeUnit);

    protected abstract void doUnLock();

}

