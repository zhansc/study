package cn.com.zhanss.juc;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * synchronized
 * 1）可见性（volatile-一个线程对变量的修改堆另外线程可见）、原子性
 * 2）可重入锁
 * 3）异常会释放锁（默认情况下）
 *
 * @author zhanss
 * @since 2022-04-10
 */
public class WhatIsSynchronized {

    @Test
    public void testJavaObjectHeader() {
        Account account = new Account();
        // 打印指定对象的类布局
        System.out.println(ClassLayout.parseInstance(account).toPrintable());

    }

    /**
     * 脏读
     * 在查询操作接口get()上加锁synchronized
     */
    @Test
    public void testDirtyRead() {
        Account account = new Account();
        new Thread(() -> account.set("zhangsan", 100)).start();

        System.out.println("当前余额"+ account.get("zhangsan"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i ++) {
            System.out.println(i+ "当前余额"+ account.get("zhangsan"));
        }
        System.out.println("end");
    }

    @Test
    public void testException() {
        LockException le = new LockException();
        new Thread(() -> le.lockException()).start();
        System.out.println("1111111111");
        new Thread(() -> le.lockException()).start();
        System.out.println("2222222222");

    }

    /**
     * synchronized锁异常时会自动释放锁，
     * 所以在逻辑处理时需要捕获异常，再做后续处理，否则其他线程会抢当前锁
     */
    public static class LockException {
        private int count = 0;

        public synchronized void lockException() {
            for (int i = 0; i < 10; i ++) {
                count ++;
            if (count == 5) {
                System.out.println("i--->"+ i + ", count--->" + count);
                int j = i/0;
            }
//                try {
//                    if (count == 5) {
//                        System.out.println("i--->"+ i);
//                        int j = i/0;
//                    }
//                } catch (Exception e) {
//                    System.out.println("i--->"+ i + ", count--->" + count);
//                    continue;
//                }
            }
            System.out.println("count--->"+ count);
        }
    }

}

class Account {
    private String name;

    private double balance;
    /**
     * 对账户写操作加锁
     * @param name
     * @param balance
     */
    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            // 写账户余额时堵塞1s
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    /**
     * 对账号查操作不加锁
     * @param name
     * @return
     */
    public synchronized double get(String name) {
        return this.balance;
    }
}
