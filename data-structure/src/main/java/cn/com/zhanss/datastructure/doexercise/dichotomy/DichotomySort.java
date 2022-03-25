package cn.com.zhanss.datastructure.doexercise.dichotomy;

import org.junit.Test;

/**
 * @author zhanshuchan
 * @desc 二分法排序
 * @date 2022/3/23
 */
public class DichotomySort {

    /**
     * 找出数组中小于指定值的最左的位置
     *
     * @param arr
     * @return
     */
    public static int nearestLeft(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        while (left <= right){
            // （left + right）存在溢出的风险
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 3, 3, 4, 5, 5, 5, 6};
        int index = nearestLeft(arr, 3);
        System.out.println("index--->" + index);
    }
}
