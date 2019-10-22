package cn.com.zhanss.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Callable创建线程
 *
 * @author zhanss
 * @since 2019/9/19
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 提供了构造方法FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
//        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread());

        // 启动线程
        new Thread(futureTask, "AA").start();
        // 多个线程争抢一个FutureTask，计算结果只会计算一次（复用），若非要计算多次，只能实例化多个FutureTask
        new Thread(futureTask, "BB").start();
        // Callable线程有延迟，导致阻塞了main主线程
//        int result2 = futureTask.get();
        System.out.println(Thread.currentThread().getName() + "=====");
        int result1 = 100;
        while (!futureTask.isDone()) {
            // 没有计算完成，就等待或其他操作
        }
        // 获取Callable线程计算结果，如果没有必要建议放在最后：如果Callable线程没有计算完成会等待，直到计算完成返回，导致阻塞；
        int result2 = futureTask.get();

        System.out.println("=====result:"+ (result1 + result2));
        // 查看当前计算机多少个处理器，多少核
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    static class MyThread implements Callable<Integer> {
        @Override
        public Integer call() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "=====Come in Callable====");
            TimeUnit.SECONDS.sleep(2);
            return 120;
        }
    }
}
