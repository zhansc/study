package cn.com.zhanss.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程绑定多条件
 *
 * @author zhanss
 * @since 2019/9/19
 */
public class ProdConsumer_Condition {

    private int number = 1;
    private Lock lock = new ReentrantLock(true);
    // 锁绑定多条件
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private void print5(int count) {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (number != 1) {
                condition1.await();
            }
            // 操作
            for(int i = 1; i <= count; i ++) {
                System.out.println(Thread.currentThread().getName()+ "\t"+ i);
            }

            // 修改状态
            number = 2;
            // 指定唤醒线程2
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    private void print10(int count) {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (number != 2) {
                // 等待
                condition2.await();
            }
            // 操作
            for(int i = 1; i <= count; i ++) {
                System.out.println(Thread.currentThread().getName()+ "\t"+ i);
            }

            // 修改状态
            number = 3;
            // 指定唤醒线程2
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    private void print15(int count) {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (number != 3) {
                // 等待
                condition3.await();
            }
            // 操作
            for(int i = 1; i <= count; i ++) {
                System.out.println(Thread.currentThread().getName()+ "\t"+ i);
            }

            // 修改状态
            number = 1;
            // 指定唤醒线程2
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProdConsumer_Condition consumerCondition = new ProdConsumer_Condition();
        new Thread(() -> {
            for(int i = 1; i <= 5; i ++) {
                consumerCondition.print5(5);
            }
        }, "A").start();
        new Thread(() -> {
            for(int i = 1; i <= 5; i ++) {
                consumerCondition.print10(10);
            }
        }, "B").start();
        new Thread(() -> {
            for(int i = 1; i <= 5; i ++) {
                consumerCondition.print15(15);
            }
        }, "C").start();
    }
}
