package cn.com.zhanss.datastructure.doexercise.binary;
/**
 * @desc 二进制运算
 * @author zhanshuchan
 * @date 2022/3/24
 */
public class XorTools {

    /**
     * 异或：
     * N ^ 0 = N
     * N ^ N = 0
     * 在arr[]中有一种数出现奇数次，其他都出现偶数次，怎么找出这种数？
     * @param arr
     */
    public static void printOddTimesNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int eor = 0;
        for (int i = 0; i < arr.length; i ++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    /**
     * 问：在arr[]中有两种数出现奇数次，其他都出现偶数次，怎么找出这两种数？
     * @param arr
     */
    public static void printOddTimesNum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int eor = 0;
        for (int i2 : arr) {
            // eor = 出现奇数次的两个数a 和 b的异或，且这两个数必不相等
            eor ^= i2;
        }
        // 提取eor 最右侧的1，这个1是(a ^ b)中取的
        int rightOne = eor & (~ eor + 1);

        int onlyOne = 0;
        for (int i1 : arr) {
            // 则a 或 b中该位置肯定有一个是1，那么先将该位置是1的这个数取出来
            if ((i1 & rightOne) != 0) {
                onlyOne ^= i1;
            }
        }
        System.out.println(onlyOne + "  "+ (eor ^ onlyOne));
    }

    /**
     * 问：二进制数a中1出现的次数？01001001110  --> 5次
     */
    public static void bit1Count(int arg) {
        int count = 0;
        int rightOne;
        while (arg != 0) {
            rightOne = arg & (~ arg + 1);
            count ++;
            arg ^= rightOne;
        }
        System.out.println(count);
    }

}
