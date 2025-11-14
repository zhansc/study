package cn.com.zhanss.javaee.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 ReentrantLock
 *
 * @author zhanss
 * @since 2019/9/29
 */
public class TestReentrantLock {

    private int tick = 1;

    Lock lock = new ReentrantLock();

    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public static void main(String[] args) {

        TestReentrantLock reentrantLock = new TestReentrantLock();

        new Thread(() -> reentrantLock.ticket1(), "A").start();

        new Thread(() -> {
            try {
                reentrantLock.ticket2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> reentrantLock.ticket3(), "C").start();
    }

    private void ticket1() {
        for (int i = 0; i < 10; i ++) {
            try {
                // 上锁
                lock.lock();
                while (tick != 1) {
                    condition1.await();
                }
                Thread.sleep(5);
                System.out.print("A");
                tick ++;

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
        for (int i = 0; i < 10; i ++) {
            try {
                lock.lock();
                while (tick != 2) {
                    condition2.await();
                }
                Thread.sleep(5);
                System.out.print("B");
                tick ++;

                condition3.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    private void ticket3() {
        for (int i = 0; i < 10; i ++) {
            try {
                lock.lock();
                while (tick != 3) {
                    condition3.await();
                }
                Thread.sleep(5);
                System.out.print("C");
                tick = 1;

                condition1.signal();
            } catch (InterruptedException e) {
            } finally {
                lock.unlock();
            }
            System.out.println("");
            if (i == 9) {
                System.out.println("Hello World");
            }
        }
    }
}

