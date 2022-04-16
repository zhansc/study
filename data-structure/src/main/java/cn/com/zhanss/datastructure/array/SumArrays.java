package cn.com.zhanss.datastructure.array;

import cn.com.zhanss.datastructure.doexercise.random.Rand2Rand;
import org.junit.Test;

/**
 * 求两个数组的和数组
 *
 * @author zhanss
 * @since 2022-04-15
 */
public class SumArrays {

    @Test
    public void testSumArrays() {
        Rand2Rand rand2Rand = new Rand2Rand();
        int[] arr1 = rand2Rand.lenRandomValueRandom(8, 20);
        print(arr1);
        int[] arr2 = rand2Rand.lenRandomValueRandom(12, 10);
        print(arr2);
        int[] sumArr = sumArrays(arr1, arr2);
        print(sumArr);
    }

    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public int[] sumArrays(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) {
            return arr2;
        }
        if (arr2 == null || arr2.length == 0) {
            return arr1;
        }
        int minLen = arr1.length;
        int maxLen = arr2.length;
        if (arr1.length > arr2.length) {
            minLen = arr2.length;
            maxLen = arr1.length;
        }
        int[] sumArr = new int[maxLen];
        for (int i = 0; i < minLen; i ++) {
            sumArr[i] = arr1[i] + arr2[i];
        }
        int j = minLen;
        while (arr1.length > minLen && j < arr1.length) {
            sumArr[j] = arr1[j];
            j ++;
        }
        while (arr2.length > minLen && j < arr2.length) {
            sumArr[j] = arr2[j];
            j ++;
        }
        return sumArr;
    }
}
