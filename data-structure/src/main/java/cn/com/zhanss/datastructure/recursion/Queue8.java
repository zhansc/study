package cn.com.zhanss.datastructure.recursion;

import org.junit.Test;

/**
 * 八皇后问题
 *
 * @author zhanss
 * @since 2020/2/2
 */
public class Queue8 {

    private int max = 8;

    private int[] map = new int[max];

    private int count = 0;

    @Test
    public void main() {
        checkin(0);
        System.out.printf("一共有多少中放%d法", count);
    }

    /**
     * 放置第 n 个皇后
     * @param n
     */
    public void checkin(int n) {
        if (n == max) {
            // 结束
            print();
            return;
        }
        for (int i = 0; i < max; i ++) {
            // 将当前皇后 n，放到该行的第 i 列
            map[n] = i;
            if (judge(n)) {
                // 不冲突，则递归校验下一个皇后
                checkin(n + 1);
            }
            // 冲突，则将第 n 个皇后放到该行的 i 列
        }
    }

    /**
     * 校验放置的第 n 个皇后是否和之前的 n-1 个皇后冲突
     * @param n 第 n 个皇后
     * @return
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i ++) {
            // 同一列 和 同一斜线上的两个皇后冲突(同一行没必要校验)
            if (map[i] == map[n] || Math.abs(n - i) == Math.abs(map[i] - map[n])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印
     */
    public void print() {
        count ++;
        for (int i = 0; i < max; i ++) {
            System.out.print(map[i] + " ");
        }
        System.out.println();
    }

}
