package cn.com.zhanss.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程问题
 *
 * @author zhanshuchan
 * @date 2024/1/23
 **/
public class ThreadPool {
    public static void main(String[] args) {
        // 8核机器
        int coreCount = 8;
        // 计算操作时间为5毫秒
        int computeTime = 5;
        // DB操作时间为100毫秒
        int dbTime = 100;
        // 假设每个核心可以处理2个线程
        int totalThreads = coreCount * 2;
        // 单核CUP中计算操作的利用率
        double rate = computeTime / (computeTime + dbTime);
        // 根据 计算 和 DB操作时间设置线程池大小
        int computeThreads = (int) (coreCount * rate);
        int dbThreads = totalThreads - computeThreads;
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(totalThreads);
        // 提交计算任务
        for (int i = 0; i < computeThreads; i++) {
            threadPool.submit(() -> {
                // 执行计算任务
            });
        }
        // 提交DB操作任务
        for (int i = 0; i < dbThreads; i++) {
            threadPool.submit(() -> {
                // 执行DB操作任务
            });
        }
        // 关闭线程池
        threadPool.shutdown();
    }

}
