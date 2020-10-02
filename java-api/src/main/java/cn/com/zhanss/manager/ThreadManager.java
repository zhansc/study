package cn.com.zhanss.manager;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadManager {

    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Test
    public void testThread() {
        executorService.execute(() -> {
            try {
                for (int i = 0; i < 100; i ++) {
                    System.out.println("----->"+ i);
                }
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void runAsync() throws Exception {
        // 无返回结果
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("run start ...");
                for (int i = 0; i < 10; i ++) {
                    for (int j = 0; j < 10; j ++) {
                        TimeUnit.SECONDS.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("run error ...");
            }
            System.out.println("run end ...");
        });

        future.get();
        System.out.println("----end-----");
    }

    @Test
    public void supplyAsync() throws Exception {
        // 有返回结果
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            executorService.execute(() -> {
                try {
                    System.out.println("run start ...");
                    for (int i = 0; i < 10; i ++) {
                        for (int j = 0; j < 10; j ++) {
                            TimeUnit.SECONDS.sleep(500);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println("=======time======= "+time);
    }
}
