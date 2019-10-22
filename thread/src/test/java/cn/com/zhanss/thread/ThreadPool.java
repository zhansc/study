package cn.com.zhanss.thread;

import java.util.concurrent.*;

/**
 * 线程池
 *
 * @author zhanss
 * @since 2019/9/19
 */
public class ThreadPool {

    public static void main(String[] args) {
        // 用ThreadPoolExecutor自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            // 默认拒绝策略AbortPolicy只能处理（5 + 3）个任务
            for(int i = 0; i < 12; i ++) {
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName()+ "\t" + "来了"));
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
