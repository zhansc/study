package cn.com.zhanss.juc;

import java.util.concurrent.TimeUnit;

/**
 * 线程
 * 启动一个线程三种方式：
 * 1）extends Thread（lambda表达式）
 * 2）implements Runnable
 * 3）线程池
 *
 * @author zhanss
 * @since 2022-04-07
 */
public class WhatIsThread {

    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i ++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    private static class T2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i ++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2");
            }
        }
    }

    public static void main(String[] args) {
        // 调Thread.run()方法，执行顺序：main() -> run() -> main()
//        new T1().run();
        // 调Thread.start()方法，执行顺序：main()和start()并行执行，Java虚拟机调用该线程的run()
//        new T1().start();
        new Thread(new T2()).start();
        new T2().run();

        for(int i = 0; i < 10; i ++) {
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }

}
