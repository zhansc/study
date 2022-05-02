package cn.com.zhanss.datastructure.sort;

import org.junit.Test;

/**
 * 索引排序
 *
 * @author zhanss
 * @since 2022-05-01
 */
public class IndexSort {

    @Test
    public void test() {
        int[] arr = new int[]{9,1,8,31,4,10,6,12};
        indexSort(arr);
        System.out.println(arr);
    }

    public void indexSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        // 创建索引数组
        int[] index = new int[array.length];
        // 初始化索引数组
        int i, j;
        for (i = 0; i < array.length; i ++) {
            index[i] = i;
        }
        // 类似于插入排序，在插入比较的过程中不断地修改index数组
        for (i = 1; i < array.length; i ++) {
            j = i;
            while (j > 0) {
                if (array[i] < array[j - 1]) {
                    index[i] --;
                    index[j - 1] ++;
                }
                j --;
            }
        }
        // 根据index数组，重排原序列
        for (i = 0; i < array.length - 1;) {
            // 如果不在位置上，则调整
            if (index[i] != i) {
                j = index[i];
                swap(array, i, j);
                index[i] = index[j];
                index[j] = j;
                // 交换之后需要再次比较
            } else {
                i ++;
            }
        }
        System.out.println(index);
    }

    private void swap(int[] arr, int i, int j) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
