package cn.com.zhanss.datastructure.linkedlist.doubly;


import org.junit.Test;

import java.util.LinkedList;

/**
 * 窗口
 *
 * @author zhanss
 * @since 2022-04-25
 */
public class LinkedListWindows {

    @Test
    public void testWindows() {
        int[] arr = new int[]{4,3,5,3,3,6,7};
        int span = 3;
        int[] windowsMax = findWindowsMax(arr, span);
        System.out.println(windowsMax);
    }

    /**
     * 双端队列实现：从左到右，保持从大到小
     *
     * @param arr
     * @param span
     * @return
     */
    public int[] findWindowsMax(int[] arr, int span) {
        if (arr == null || arr.length < span) {
            return null;
        }
        int[] result = new int[arr.length - span + 1];
        int index = 0;
        // 队列中存放的是数组的下标
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int R = 0; R < arr.length; R ++) {
            // 将arr[R]放在队列中比它大的数的后面，或直到为空入队
            while (!linkedList.isEmpty() && arr[linkedList.peekLast()] <= arr[R]) {
                // 弹出所有小于当前arr[R]的下标
                linkedList.pollLast();
            }
            // 仅保留当前跨度中最大值的下标
            linkedList.addLast(R);
            // 窗口跨度是span，跨度之外的第一个下标为对头的下标，需要弹出（过期了）
            if (linkedList.peekFirst() == (R - span)) {
                linkedList.pollFirst();
            }
            // 满足窗口跨度为span时开始统计
            if (R >= (span - 1)) {
                result[index ++] = arr[linkedList.peekFirst()];
            }
        }
        return result;
    }

}
