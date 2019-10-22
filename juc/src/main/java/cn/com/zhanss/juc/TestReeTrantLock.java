package cn.com.zhanss.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试ReenTranTlock
 *
 * @author zhanss
 * @since 2019/9/29
 */
public class TestReeTrantLock {

    private int tick = 2;

    Lock lock = new ReentrantLock();

    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public static void main(String[] args) {

        TestReeTrantLock reeTrantLock = new TestReeTrantLock();

        new Thread(() -> reeTrantLock.ticket1(), "AAA").start();

        new Thread(() -> {
            try {
                reeTrantLock.ticket2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();

        new Thread(() -> reeTrantLock.ticket3(), "CCC").start();
    }

    private void ticket1() {
        while (true) {
            try {
                // 上锁
                lock.lock();
                while (tick != 0) {
                    condition1.await();
                }
                Thread.sleep(50);
                System.out.println(Thread.currentThread().getName()+ tick++);
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
    }

    private void ticket2() throws InterruptedException {
        while (true) {
            try {
                lock.lock();
                while (tick != 1) {
                    condition2.await();
                }
                Thread.sleep(50);
                System.out.println(Thread.currentThread().getName() + tick++);
                condition3.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    private void ticket3() {
        while (true) {
            try {
                lock.lock();
                while (tick != 2) {
                    condition3.await();
                }
                Thread.sleep(50);
                tick = 0;
                System.out.println(Thread.currentThread().getName()+ "====" + tick);
                condition1.signal();
            } catch (InterruptedException e) {
            } finally {
                lock.unlock();
            }
        }
    }
}

