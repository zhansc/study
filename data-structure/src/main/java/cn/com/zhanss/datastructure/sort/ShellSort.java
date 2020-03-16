package cn.com.zhanss.datastructure.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author zhanss
 * @since 2020/2/16
 */
public class ShellSort {

    @Test
    public void main() {

        int[] arr = {12, 4, -1, 122, 44, -4, -90, 8, 344, -5, 22, 9};
//        int[] arr = {12, 4, -1, 122};
        shellSort2(arr);
        System.out.println("希尔排序后：\n"+ Arrays.toString(arr));
    }

    /**
     * 插入时采用交换法
     * @param arr
     */
    private void shell(int[] arr) {
        /**
         * 原数组 12, -1, 4, 122
         * gap = arr.length / 2
         * 第一轮 4, -1, 12, 122
         * gap = gap / 2
         * 第二轮 -1, 4, 12, 122
         *
         */
        int insertVal;
        // 增量gap，逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 遍历各组中每个元素（共gap组，每组[arr.length / gap]个元素），步长 5
            for (int j = gap; j < arr.length; j ++) {
                // 每组 [arr.length / gap] 个元素
                for (int i = j - gap; i >= 0; i -= gap) {
                    if (arr[i] > arr[i + gap]) {
                        insertVal = arr[i];
                        arr[i] = arr[i + gap];
                        arr[i + gap] = insertVal;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序：移位法(插入排序)
     * @param arr
     */
    private void shellSort2(int[] arr) {
        // 增量gap，逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i ++) {
                int j = i;
                int temp = arr[gap];
                if (arr[j] < arr[j - gap]) {
                    while ((j - gap) >= 0 && arr[j] < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 即找到要移位的地方
                    arr[j] = temp;
                }
            }
        }
    }
}
