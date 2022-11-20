package cn.com.zhanss.thread;

import cn.com.zhanss.datastructure.doexercise.random.Rand2Rand;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * fork/join
 *
 * @author zhanss
 * @since 2022-04-14
 */
public class ForkJooin {

    /**
     * 通过fork/join实现求和任务
     */
    public static class CountTask extends RecursiveTask<List<Integer>> {
        /**
         * 最小阈值
         */
        private static final int taskLimit = 10;

        private int[] arr;

        private int start;

        private int end;

        private int findNum;

        public CountTask(int[] arr, int findNum, int start, int end) {
            this.arr = arr;
            this.findNum = findNum;
            this.start = start;
            this.end = end;
        }

        @Override
        protected List<Integer> compute() {
            List<Integer> finalNum = new ArrayList<>();
            if ((end - start) <= taskLimit) {
//                System.out.println("任务足够小，就开始计算");
                for (int i = start; i <= end; i ++) {
                    if (findNum == arr[i]) {
                        finalNum.add(i);
                    }
                }
            } else {
                int middle = start + ((end - start) >> 1);
                CountTask leftTask = new CountTask(arr, findNum, start, middle);
                CountTask rightTask = new CountTask(arr,findNum, middle + 1, end);
                // 任务再分配
                leftTask.fork();
                rightTask.fork();

                // 等待子任务执行完成，进行合并
                if (!CollectionUtils.isEmpty(leftTask.join())) {
                    finalNum.addAll(leftTask.join());
                }
                if (!CollectionUtils.isEmpty(leftTask.join())) {
                    finalNum.addAll(rightTask.join());
                }
            }
            return finalNum;
        }

        public static Long sumArr(int[] arr) {
            if (arr == null || arr.length == 0) {
                return 0L;
            }
            long sum = 0L;
            for (int i = 0; i < arr.length; i ++) {
                sum += arr[i];
            }
            return sum;
        }

        public static void main(String[] args) {
            // 生成随机数组
            Rand2Rand rand2Rand = new Rand2Rand();
            int[] randomArr = rand2Rand.lenRandomValueRandom(10000000, 10);

            long start = System.currentTimeMillis();
//            Long sum = sumArr(randomArr);
            long end = System.currentTimeMillis();
//            System.out.println("sumArr end time=" + (end - start));
            int random100 = randomArr[100];
            System.out.println("sum=" + random100 + ",数组长度="+ randomArr.length);

            ForkJoinPool forkJoinPool = new ForkJoinPool();
            CountTask countTask = new CountTask(randomArr, random100, 0, randomArr.length - 1);
            start = System.currentTimeMillis();
            ForkJoinTask<List<Integer>> result = forkJoinPool.submit(countTask);

            try {
                System.out.println("result=" + result.get());
                end = System.currentTimeMillis();
                System.out.println("countTask end time=" + (end - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
