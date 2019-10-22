package cn.com.zhanss.juc;

import java.util.concurrent.locks.Lock;

/**
 * 测试
 *
 * @author zhanss
 * @since 2019/9/29
 */
public class TwinsLockTest {
    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        Thread.sleep(1000L);
                        System.out.println(Thread.currentThread());
                        Thread.sleep(1000L);
                    } catch (Exception ex) {
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i ++) {
            Worker w = new Worker();
            w.start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200L);
                    System.out.println();
                } catch (Exception ex) {
                }
            }
        }).start();

        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
