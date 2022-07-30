package io.github.support.lock;

import io.github.support.function.DoFunction;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.function.BooleanSupplier;

public interface EnhanceLock extends Lock {

    void ifLockFail(DoFunction d);

    void ifLockFail(long time, TimeUnit timeUnit, DoFunction d);

    boolean ifAllowLock(BooleanSupplier supplier);

    @Override
    default void lockInterruptibly() {
        throw new UnsupportedOperationException();
    }

    @Override
    default Condition newCondition() {
        throw new UnsupportedOperationException();
    }

}
