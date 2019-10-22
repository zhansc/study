package cn.com.zhanss.thread;

import java.util.concurrent.TimeUnit;

/**
 * 死锁 + 排查
 *
 * @author zhanss
 * @since 2019/9/19
 */
public class DeadLock implements Runnable{

    private String lockA;
    private String lockB;

    private DeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()+ "\t 持有"+ lockA +"的锁，还想获得"+ lockB);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName()+ "\t 持有"+ lockB +"的锁，还想获得"+ lockA);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

        new Thread(new DeadLock("lockA", "lockB"), "AAA").start();
        new Thread(new DeadLock("lockB", "lockA"), "BBB").start();
    }
}
