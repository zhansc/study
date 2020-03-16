package cn.com.zhanss.datastructure.sort;

import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author zhanss
 * @since 2020/2/6
 */
public class BubbleSort {


    @Test
    public void main() {
        int[] arr = {4, 7, 20, 0, 1, 10, 3, 6, 13};
        bubble(arr);
        System.out.println(Arrays.toString(arr));
    }

    private void bubble(int[] arr) {
        if (ObjectUtils.isEmpty(arr)) {
            return;
        }
        // 辅助变量
        int temp;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i ++) {
            for (int j = 0; j < arr.length - 1 - i; j ++) {
                // 将更小值逐渐冒泡到前面
                if (arr[j] > arr[j+1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            // 有一轮没有做交换，说明已经是有序的
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
