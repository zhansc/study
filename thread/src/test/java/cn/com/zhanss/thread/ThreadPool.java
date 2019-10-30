package cn.com.zhanss.thread;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池：1000个并发报价，报价增长区间【1，200】，先达到40000成交，没到4000提前结束
 *
 * @author zhanss
 * @since 2019/9/19
 */
public class ThreadPool {

    private volatile AtomicInteger price = new AtomicInteger(1);

    @Test
    public void main() {
        // 用ThreadPoolExecutor自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                100,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(500),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        Random random = new Random();
        try {
            // 默认拒绝策略AbortPolicy只能处理（5 + 3）个任务
            for (int i = 0; i < 1000; i++) {
                    while (price.get() >= 4000) {
                        System.out.println(Thread.currentThread().getName() + "\t" + price.get() + "\t成交！");
                        Thread.currentThread().join();
                    }
                    threadPool.execute(() -> {
                        try {
                            Thread.sleep(50);
                            System.out.println(Thread.currentThread().getName() + "\t" + price.get());
                            price.addAndGet(random.nextInt(200));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 归还线程
            threadPool.shutdown();
        }
    }

    private static void threadPoolInit() {
        // 定长线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 单线程池
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 可缓存线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // 定长线程池，可定时周期性执行任务
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        try {
            service.scheduleAtFixedRate(() -> System.out.println("周期性执行"), 3, 1, TimeUnit.SECONDS);
//            for(int i = 0; i < 10; i ++) {
//                threadPool.execute(() -> System.out.println(Thread.currentThread().getName()+ "\t" + "来了"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 归还线程
            threadPool.shutdown();
        }
    }

}
