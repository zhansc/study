package cn.com.zhanss.thread;

import org.springframework.util.StringUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者-阻塞队列
 *
 * @author zhanss
 * @since 2019/9/19
 */
public class ProdConsumer_BlockQueue {
    // 生产开关
    private volatile boolean FLAG = true;
    // 原子整型
    private AtomicInteger atomicInteger = new AtomicInteger();
    // 阻塞队列：通用，适配所有的阻塞队列
    private BlockingQueue<String> blockingQueue;

    // 让用户自定义
    public ProdConsumer_BlockQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        // 输出下传入的阻塞队列类型
        System.out.println(blockingQueue.getClass().getName());
    }

    private void prod() {
        try {
            String data;
            boolean resultVal;
            while (FLAG) {
                data = atomicInteger.incrementAndGet() + "";
                // 2秒钟生产一个
                resultVal = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if (resultVal) {
                    System.out.println(Thread.currentThread().getName() + "\t 插入队列成功" + data);
                } else {
                    System.out.println(Thread.currentThread().getName() + "\t 插入队列失败" + data);
                }
                // 睡一秒钟
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println(Thread.currentThread().getName() + "\t 主程序叫停了，FLAG=false，生产停止");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void consumer() {
        try {
            String data;
            while (FLAG) {
                // 等待2秒
                data = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if (StringUtils.isEmpty(data)) {
                    FLAG = false;
                    System.out.println(Thread.currentThread().getName() + "\t 超过2秒没有取到内容，消费退出");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "\t 消费队列成功" + data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        FLAG = false;
    }

    public static void main(String[] args) {
        ProdConsumer_BlockQueue prodConsumerBlockQueue = new ProdConsumer_BlockQueue(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产者启动成功");
            try {
                prodConsumerBlockQueue.prod();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费者启动成功");
            System.out.println();
            System.out.println();
            try {
                prodConsumerBlockQueue.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println();
            System.out.println();
            System.out.println("5秒钟决定停止了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        prodConsumerBlockQueue.stop();
    }

}
