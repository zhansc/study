package cn.com.zhanss.datastructure.sort;

import org.junit.Test;

/**
 * @desc 数组排序
 * @author zhanshuchan
 * @date 2022/3/29
 */
public class ArraySort {

    @Test
    public void testLtSum() {
        int[] arr = new int[]{1,3,4,2,5};
        int sum = ltSum(arr);
        System.out.println("小和sum--->"+ sum);

        int mergeSum = processMerge(arr, 0, arr.length - 1);
        System.out.println("小和mergeSum--->"+ mergeSum);
    }

    /**
     * 求小和
     * 1左边比1小的数，没有
     * 3左边 1
     * 4左边 1，3
     * 2左边 1
     * 5左边 1，3，4，2
     * @param arr
     * @return
     */
    public int ltSum(int[] arr) {
        if (arr == null || arr.length == 1) {
            System.out.println("不存在小和！！");
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < arr.length; i ++) {
            for (int j = 0; j < i; j ++) {
                if (arr[j] < arr[i]) {
                    sum += arr[j];
                }
            }
        }
        return sum;
    }

    /**
     * 归并排序
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public int processMerge(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        // 防止(left + right)溢出int.MAX
        int middle = left + ((right - left) >> 1);
        return processMerge(arr, left, middle)
             + processMerge(arr, middle + 1, right)
             + merge(arr, left, middle, right);
    }

    public int merge(int[] arr, int left, int middle, int right) {
        int index = 0;
        int ans = 0;
        int p = middle + 1;
        int q = left;
        int[] help = new int[right - left + 1];
        while (left <= middle && p <= right) {
            if (arr[left] < arr[p]) {
                // 此处(right - p + 1) 跨度不能是(right - middle)，因为p 会自增，跨度是变化的
                ans += (right - p + 1) * arr[left];
                help[index ++] = arr[left ++];
            } else {
                help[index ++] = arr[p ++];
            }
        }
        while (left <= middle) {
            help[index ++] = arr[left ++];
        }
        while (p <= right) {
            help[index ++] = arr[p ++];
        }
        // 排序后数组写回arr
        for (int i = 0; i < help.length; i ++) {
            arr[q + i] = help[i];
        }
        return ans;
    }

    /**
     * 快速排序
     * @param arr
     * @param num
     * @return
     */
    public int[] quickSort(int[] arr, int num) {
        // TODO

        return arr;
    }
}
