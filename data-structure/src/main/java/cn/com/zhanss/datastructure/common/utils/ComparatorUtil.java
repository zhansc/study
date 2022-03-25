package cn.com.zhanss.datastructure.common.utils;

import java.util.Arrays;

/**
 * @desc 对数器
 * 1、你想要测试一个实现复杂度不好的方法a，怎么测试对不对呢？
 * 2、但是很容易实现方法b，一定是正确的
 * 3、实现一个随机样本产生器
 * 4、让方法a和方法b跑相同的随机样本，看得到的结果是否一样，如果不一致，则进行人工干预、比对、debug调试，直到一致
 * 5、当样本数量很大时，对比测试的结果一致，可以确定方法a实现是正确的
 * @author zhanshuchan
 * @date 2022/3/22
 */
public class ComparatorUtil {

    /**
 * 生成随机长度随机大小的数组
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // 指定数组的大小为随机值[0, maxSize - 1]
        int[] arr = new int[(int) (Math.random() * maxSize)];
        for (int i = 0; i < arr.length; i ++) {
            // 指定数据值为[-maxValue + 1, maxValue - 1]
            arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
        }
        return arr;
    }

    /**
     * 自定义数据复制
     * @param oldArr
     * @return
     */
    public static int[] copyArray(int [] oldArr) {
        if (oldArr == null || oldArr.length == 0) {
            return new int[0];
        }
        int[] newArr = new int[oldArr.length];
        for (int i = 0; i < oldArr.length; i ++) {
            newArr[i] = oldArr[i];
        }
        return newArr;
    }

    /**
     * 数组比较器
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean comparator(int[] arr1, int[] arr2) {
        // 同时为空
        if (arr1 == null && arr2 == null) {
            return true;
        }
        // 有一个为空
        if (arr1 == null || arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i ++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i ++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        // 测试次数
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean lower = false;
        for (int i = 0; i < testTime; i ++) {
            // 生成随机数组
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            // java自带的排序
            Arrays.sort(arr1);
            // 自定义冒泡排序
            bubbleSort(arr2);
            // 数组比较
            if (!comparator(arr1, arr2)) {
                lower = true;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(lower ? "lower!" : "nice!");
    }

    /**
     * 冒泡排序
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; i ++) {
            int j = i + 1;
            for (; j < arr.length; j ++) {
                // 交换
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
