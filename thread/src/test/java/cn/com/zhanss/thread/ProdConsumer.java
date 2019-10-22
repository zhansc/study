package cn.com.zhanss.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信之生产者和消费者
 *
 * @author zhanss
 * @since 2019/9/18
 */
public class ProdConsumer {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void increment() {
        lock.lock();
        try {
            // 1、判断：多线程可能存在中断和虚假唤醒，所以判断使用while而不是if
            while (number != 0) {
                // 等待，不能生产
                condition.await();
            }
            // 2、干活
            number ++;
            System.out.println(Thread.currentThread().getName()+ "\t" + number);

            // 3、通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number --;
            System.out.println(Thread.currentThread().getName()+ "\t" + number);

            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProdConsumer prodConsumer = new ProdConsumer();
        // 线程  操作  资源类
        new Thread(() ->{
           for(int i = 1; i <= 5; i ++) {
               try {
                   prodConsumer.decrement();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        }, "AA").start();

        new Thread(() ->{
            for(int i = 1; i <= 5; i ++) {
                try {
                    prodConsumer.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }
}
