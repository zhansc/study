package cn.com.zhanss.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @desc 自定义锁
 * @author zhanshuchan
 * @date 2020/10/24
 */
public class MyLock implements Lock {

    private Helper helper = new Helper();

    private static class Helper extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -3135165585598193610L;
        @Override
        public boolean tryAcquire(int args) {
            // 获取锁状态
            int state = getState();
            if (state == 0) {
                compareAndSetState(0, args);
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else if (getExclusiveOwnerThread() == Thread.currentThread()) {
                // 可重入锁
                compareAndSetState(0, args);
                return true;
            }
            return false;
        }

        @Override
        public boolean tryRelease(int args) {
            // 获取锁状态
            int state = getState();
            if (state > 0) {
                // 释放锁
                setState(0);
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }
    }

    @Override
    public void lock() {
        helper.tryAcquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        helper.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
