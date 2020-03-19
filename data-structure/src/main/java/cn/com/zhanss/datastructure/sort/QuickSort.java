package cn.com.zhanss.datastructure.sort;

import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author zhanss
 * @since 2020/3/10
 */
public class QuickSort {

    @Test
    public void main() {
        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 12, -90, 43, -908, 76};
        quick(arr, 0, arr.length - 1);
        System.out.println("快速排序后："+ Arrays.toString(arr));
    }

    public void quick(int[] arr, int left, int right) {
        if (ObjectUtils.isEmpty(arr)) {
            return;
        }
        int l = left;
        int r = right;
        int mid = arr[(left + right) / 2];
        int temp;
        // 从两端向mid 循环
        while (l < r) {
            // 在mid 左边找到一个比mid 大的即退出
            while (arr[l] < mid) {
                l += 1;
            }
            // 在mid 右边找到一个比mid 小的即退出
            while (arr[r] > mid) {
                r -= 1;
            }
            // 说明左边全部都是小于mid，右边全部都是大于mid
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 若交换后arr[l] == mid 则 r -- 前移
            if (arr[l] == mid) {
                r -= 1;
            }
            // 后移
            if (arr[r] == mid) {
                l += 1;
            }
        }
        System.out.println("l-->"+ l + "<--r-->"+ r);
        // 避免溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 递归mid 左边
        if (left < r) {
            quick(arr, left, r);
        }
        // 递归mid 右边
        if (l  < right) {
            quick(arr, l, right);
        }

    }

}
