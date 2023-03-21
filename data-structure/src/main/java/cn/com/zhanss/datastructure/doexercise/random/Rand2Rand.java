package cn.com.zhanss.datastructure.doexercise.random;

import cn.com.zhanss.datastructure.common.entity.Node;
import org.junit.Test;

/**
 * @author zhanshuchan
 * @desc 随机发生器生成
 * @date 2022/2/17
 */
public class Rand2Rand {

    /**
     * [1, 5]随机发生器
     *
     * @return
     */
    public int f() {
        // [0, 4] + 1
        return (int) (Math.random() * 3) + 1;
    }

    /**
     * 0 或 1等概率随机发生器
     *
     * @return
     */
    public int f01() {
        int ans;
        do {
            ans = f();
        } while (ans == 2);
        return ans < 2 ? 0 : 1;
    }

    /**
     * 000 ~ 111即[0, 7]等概率发生器
     *
     * @return
     */
    public int f07() {
        return (f01() << 2)/*第一位0或1等概率出现*/
                + (f01() << 1)/*第二位0或1等概率出现*/
                + f01()/*第三位0或1等概率出现*/;
    }

    /**
     * [0, 6]概率随机发生器
     *
     * @return
     */
    public int f06() {
        int ran;
        do {
            ran = f07() - 1;
        } while (ran < 0);
        System.out.println("方案一#######[0, 6]等概率#######"+ran);

        int ans;
        do {
            ans = f07();
        } while (ans == 7);
        return ans;
    }

    /**
     * [1, 7]概率随机发生器
     *
     * @return
     */
    public int g17() {
        return f06() + 1;
    }

    public int f34() {
        return Math.random() < 0.34 ? 0 : 1;
    }

    /**
     * f()函数返回的结果是0 或 1，出现0 的概率是P，出现1 的概率是 1-P，
     * 问怎么仅用f()得到g()函数返回的结果是0 或 1，且出现0 的概率和出现1的概率相等？
     * @return
     */
    public int g01() {
        int ans;
        do {
            ans = f34();
        } while (ans == f34());
        return ans;
    }

    @Test
    public void test() {
        for (int i = 0; i < 20; i ++) {
            System.out.println(g01());
        }
    }
    /**
     * 指定最大数组长度和数据组最大值，返回一个随机数组
     *
     * @param maxLen
     * @param maxValue
     * @return
     */
    public static int[] lenRandomValueRandom(int maxLen, int maxValue) {
        int arrLen = (int) (Math.random() * maxLen);
        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

}
