package io.github.support.lock;

import io.github.support.function.DoFunction;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

/**
 * {@link EnhanceLock} 的基础实现
 *
 * @author Joker
 * @date 2022/7/31 17:04
 * @since 0.0.1
 */
public abstract class AbstractLock implements EnhanceLock {

    private BooleanSupplier booleanSupplier;

    public AbstractLock(BooleanSupplier booleanSupplier) {
        this.booleanSupplier = booleanSupplier;
    }

    @Override
    public void lock() {
        lock(0, null);
    }

    @Override
    public boolean tryLock() {
        return lock(0, null);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return lock(time, unit);
    }

    @Override
    public void unlock() {
        doUnLock();
    }

    @Override
    public void ifLockFail(DoFunction doFunction) {
        if (!lock(0, null)) {
            doFunction.doIt();
        }
    }

    @Override
    public void ifLockFail(long time, TimeUnit timeUnit, DoFunction doFunction) {
        boolean suc = false;
        try {
            suc = lock(time, timeUnit);
        } catch (Throwable e) {
            //nothing
        }
        if (!suc) {
            doFunction.doIt();
        }
    }

    @Override
    public boolean allowLock() {
        return allowLock(getBooleanSupplier());
    }

    /**
     * 当 {@link AbstractLock#getBooleanSupplier()} 校验结果为false时，此方法则直接返回false 不会进行加锁操作
     * 当结果为true时，则会进行加锁操作 其返回结果并不百分之百保证加锁成功或失败。
     * 当 {@link AbstractLock#doLock(long, TimeUnit)} ()} 逻辑使用第三方框架实现，则返回的内容为第三方框架的执行操作结果
     * 或 用户自行实现的逻辑
     *
     * @param time 加锁时长
     * @param unit 时间格式
     * @return 加锁是否成功
     */
    private boolean lock(long time, TimeUnit unit) {
        return allowLock() && doLock(time, unit);
    }

    private BooleanSupplier getBooleanSupplier() {
        return booleanSupplier;
    }

    public void setBooleanSupplier(BooleanSupplier booleanSupplier) {
        this.booleanSupplier = booleanSupplier;
    }

    protected abstract boolean allowLock(BooleanSupplier booleanSupplier);

    protected abstract boolean doLock(long time, TimeUnit timeUnit);

    protected abstract void doUnLock();

}

