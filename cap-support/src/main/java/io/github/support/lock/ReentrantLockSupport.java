package io.github.support.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;

/**
 * {@link EnhanceLock} 的ReentrantLock实现
 *
 * @author Joker
 * @date 2022/7/31 17:04
 * @since 0.0.1
 */
public class ReentrantLockSupport extends AbstractLock {

    private final ReentrantLock REENTRANT_LOCK;

    public ReentrantLockSupport(ReentrantLock reentrantLock) {
        this(reentrantLock, null);
    }

    public ReentrantLockSupport(ReentrantLock reentrantLock, BooleanSupplier booleanSupplier) {
        super(booleanSupplier);
        REENTRANT_LOCK = reentrantLock;
    }

    @Override
    protected boolean allowLock(BooleanSupplier booleanSupplier) {
        return true;
    }

    @Override
    protected boolean doLock(long time, TimeUnit timeUnit) {
        if (time > 0 && timeUnit != null) {
            return reentrantLock(time, timeUnit);
        } else {
            return reentrantLock();
        }
    }

    private boolean reentrantLock() {
        return REENTRANT_LOCK.tryLock();
    }

    private boolean reentrantLock(long time, TimeUnit timeUnit) {
        try {
            return REENTRANT_LOCK.tryLock(time, timeUnit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    protected void doUnLock() {
        REENTRANT_LOCK.unlock();
    }

}
