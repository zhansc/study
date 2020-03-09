package cn.com.zhanss.datastructure.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author zhanss
 * @since 2020/2/16
 */
public class InsertSort {

    @Test
    public void maint() {

        int[] arr = {101, 89, 2, 45, -12, 34, 223, 10, -54};
        insert(arr);
        System.out.println("插入排序后：");
        System.out.println(Arrays.toString(arr));
    }

    private void insert(int[] arr) {
        int insertVal;
        int insertIndex;
        int i = 1;
        for (; i < arr.length; i ++) {
            // 保存当前需要插入的value 和 开始插入的位置
            insertVal = arr[i];
            insertIndex = i - 1;
            // 避免越界
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                // 将大于当前insertVal 的值向后移动一位
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex --;
            }
            // 不需要插入
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }

    }

}
