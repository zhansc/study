package cn.com.zhanss.datastructure.sort;

import cn.com.zhanss.datastructure.heap.MyHeap;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @desc 数组排序
 * @author zhanshuchan
 * @date 2022/3/29
 */
public class ArraySort {

    @Test
    public void test() {
        int[] arr = new int[]{1,3,4,2,5};
        int sum = ltSum(arr);
        System.out.println("小和sum--->"+ sum);

        int mergeSum = processMerge(arr, 0, arr.length - 1);
        System.out.println("小和mergeSum--->"+ mergeSum);

        System.out.println("归并数组打印：");
        print(arr);
        System.out.println("\n快排数组打印：");
        int[] arr1 = new int[]{5,7,2,1};
        quickSort(arr1);
        print(arr1);

        int[] arr2 = new int[]{3,2,1,5,6,4};
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < arr2.length; i ++) {
            priorityQueue.add(arr2[i]);
        }
        heapSort(arr2);
        print(arr2);

        int[] maxSubArr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int i = maxSubArray(maxSubArr);
        System.out.println("=====i======>"+ i);
    }

    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            System.out.print(" "+ arr[i]);
        }
        System.out.println();
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
     * 归并排序，并求小和--左边数比自己小的累计和
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
     * 随机取数组中一个元素为划分值
     * @param arr
     * @return
     */
    public void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        processQuick(arr, 0, arr.length - 1);
    }

    public void processQuick(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int less = left - 1;
        int more = right + 1;
        // 以数组最后一个元素为划分值时间复杂度O(N^2)，将划分值改为随机值时间复杂度为O(N*logN)
//        int randomNum = arr[right];
        int randomNum = randomNum(arr, left, right);
        // <[i]区  [less  ==[i]区  more]   >[i]区
        for (int i = left; i < more;) {
            if (arr[i] < randomNum) {
                swap(arr, i ++, ++ less);
            } else if (arr[i] > randomNum) {
                // 新换过来的元素需要重新比较
                swap(arr, i, -- more);
            } else {
                // 相等区
                i ++;
            }
        }
        // less 是从-1开始的，且遍历的时候是不包含最后一个的
        processQuick(arr, left, less);
        // more 是从arr.length开始的，下次递归是不需要减一的
        processQuick(arr, more, right);
    }

    /**
     * 交换
     * @param arr 指定数组
     * @param arrIndex 比较的位置
     * @param numIndex 划分值位置
     */
    public void swap(int[] arr, int arrIndex, int numIndex) {
        int temp = arr[arrIndex];
        arr[arrIndex] = arr[numIndex];
        arr[numIndex] = temp;
    }

    /**
     * 取数组中随机一个元素
     * @param arr
     * @return
     */
    public int randomNum(int[] arr, int left, int right) {
        int i = new Random().nextInt(right - left + 1) + left;
        return arr[i];
    }

    /**
     * 堆排序
     * @param arr
     * @return
     */
    public void heapSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        MyHeap heap = new MyHeap();
        for (int i = 0; i < arr.length; i ++) {
            heap.heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        while (heapSize > 0) {
            swap(arr, 0, -- heapSize);
            heap.heapify(arr,0, heapSize);
        }
    }

    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 子数组 是数组中的一个连续部分。
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * https://leetcode-cn.com/problems/maximum-subarray/
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right;
        Integer max = null;
        while (left < nums.length) {
            int count = 0;
            right = left;
            while (right < nums.length) {
                count += nums[right];
                max = max == null ? count : Math.max(max, count);
                right ++;
            }
            left ++;
        }
        return max;
    }
}
