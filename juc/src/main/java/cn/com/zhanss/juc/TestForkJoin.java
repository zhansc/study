package cn.com.zhanss.juc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 测试“任务窃取”模式
 *
 * @author zhanss
 * @since 2019/9/30
 */
public class TestForkJoin {

    public static void main(String[] args) {
        Instant start = Instant.now();
        long num = 50000000000L;

        ForkJoinTask<Long> forkJoinTask = new ForkJoinSum(0L, num);
        ForkJoinPool pool = new ForkJoinPool();
        Long sum = pool.invoke(forkJoinTask);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("java7消耗时间："+ Duration.between(start, end).toMillis());

        TestForkJoin testForkJoin = new TestForkJoin();
        testForkJoin.forkJoin8(num);
    }

    private void forkJoin8(long num) {
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0L, num)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("java8消耗时间："+ Duration.between(start, end).toMillis());
    }

}

class ForkJoinSum extends RecursiveTask<Long> {

    private long start;

    private long end;

    private static final long THRESHOLD = 100000L;

    public ForkJoinSum(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0L;
        long length = end - start;
        // 任务拆分到临界值，开始计算
        if (length <= THRESHOLD) {
            for (long i = start; i < end; i ++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinSum left = new ForkJoinSum(start, middle);
            // 任务拆分
            left.fork();

            ForkJoinSum rigth = new ForkJoinSum(middle, end);
            rigth.fork();
            // 合并
            return left.join() + rigth.join();
        }
    }
}