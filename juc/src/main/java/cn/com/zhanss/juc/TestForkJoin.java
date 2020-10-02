package cn.com.zhanss.juc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;
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

        ForkJoinTask<Long> forkJoinTask = null;
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

    @Test
    public void testForkJoin() {
        List<String> kdtIdList = Arrays.asList("12345", "7890", "754332", "322", "665", "4232");

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinSplit forkJoinSplit = new ForkJoinSplit(0, kdtIdList.size() -1,123, kdtIdList);
        forkJoinPool.submit(forkJoinSplit);
        forkJoinSplit.join();
        // 关闭线程池
        forkJoinPool.shutdown();
    }

    @Test
    public void testProcessor() {
        AppPushSendDetail appPushSendDetail = AppPushSendDetail.builder()
                .createdAt(new Date(1597947858L))
                .build();
        System.out.println("appPushSendDetail---->"+ appPushSendDetail);

    }


}

@AllArgsConstructor
class ForkJoinSplit extends RecursiveAction {

    private static final int THRESHOLD = 500;

    private int start;

    private int end;

    private int batchId;

    private List<String> kdtIdList;

    @Override
    protected void compute() {
        // 任务拆分到临界值，开始处理
        if (kdtIdList.size() > THRESHOLD) {
            // 任务拆分
            int middle = (start + end) / 2;
            ForkJoinSplit left = new ForkJoinSplit(start, middle, batchId, kdtIdList);
            ForkJoinSplit right = new ForkJoinSplit(middle, end, batchId, kdtIdList);
            left.fork();
            right.compute();
            left.join();
        } else {
            // 并行
            List<AppPushSendDetail> appPushSendDetails = kdtIdList.parallelStream()
                    .map(item -> AppPushSendDetail.builder()
                            .batchId(batchId)
                            .kdtId(Long.valueOf(item))
                            .adminId(0L)
                            .status(0)
                            .build())
                    .collect(Collectors.toList());
            System.out.println("appPushSendDetails--->"+ appPushSendDetails);
        }
    }
}
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class AppPushSendDetail {
    private Long id;
    private Integer batchId;
    private Long kdtId;
    private Long adminId;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
}