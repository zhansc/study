package cn.com.zhanss.datastructure.heap;

import org.junit.Test;

/**
 * 自定义大根堆
 *
 * @author zhanss
 * @since 2022-04-09
 */
public class MyHeap {

    private Integer heapSize = 0;

    private int[] arr = new int[10];

    public MyHeap(int[] arr) {
        this.arr = arr;
    }
    public MyHeap() {
    }

    public MyHeap(int capacity) {
        this.arr = new int[capacity];
    }

    /**
     * 堆中插入元素
     * @param num
     * @return
     */
    public boolean heapInsert(Integer num) {
        if (num == null) {
            return false;
        }
        int index = heapSize, parentIndex;
        arr[heapSize ++] = num;
        while (index > 0) {
            parentIndex = (index - 1) / 2;
            if (arr[index] > arr[parentIndex]) {
                swap(arr, index, parentIndex);
            }
            index = parentIndex;
        }
        return true;
    }

    public static void main(String[] args) {
        MyHeap myHeap = new MyHeap(10);
        myHeap.heapInsert(185);
        myHeap.heapInsert(165);
        myHeap.heapInsert(170);
        myHeap.pop();
        myHeap.pop();
        myHeap.pop();
        System.out.println("end");
    }

    /**
     * 从堆顶弹出元素（弹出最大值）
     * @return
     */
    public Integer pop() {
        if (heapSize == 0) {
            return null;
        }
        Integer ans = arr[0];
        // 末尾数据覆盖堆顶元素
        swap(arr, 0, -- heapSize);
        // 堆化
        heapify(arr, 0, heapSize);
        return ans;
    }

    /**
     * 查看对顶弹出元素
     * @return
     */
    public Integer peek() {
        if (heapSize == 0) {
            return null;
        }
        return arr[0];
    }

    public boolean isEmpty() {
        return arr == null || heapSize == 0;
    }

    /**
     * 堆化
     * 堆顶元素一直下沉，直到没有左孩子了或不再小于左右孩子节点值
     */
    public void heapify(int[] arr, int index, int heapSize) {
        if (arr == null) {
            return;
        }
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 右节点大于左节点，最大值为右节点，否则为左节点
            int largest = (left + 1) < heapSize && arr[left + 1] > arr[left] ? (left + 1) : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                // 不再需要下沉
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private void swap(int[] arr, int left, int right) {
        if (arr == null) {
            return;
        }
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
