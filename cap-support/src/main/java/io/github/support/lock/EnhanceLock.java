package io.github.support.lock;

import io.github.support.function.DoFunction;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 * 扩展 {@link Lock} 提供额外的加锁失败处理逻辑
 *
 * @see Lock
 * @author Joker
 * @date 2022/7/31 17:04
 * @since 0.0.1
 */
public interface EnhanceLock extends Lock {

    void ifLockFail(DoFunction d);

    void ifLockFail(long time, TimeUnit timeUnit, DoFunction d);

    boolean allowLock();

    @Override
    default void lockInterruptibly() {
        throw new UnsupportedOperationException();
    }

    @Override
    default Condition newCondition() {
        throw new UnsupportedOperationException();
    }

}
