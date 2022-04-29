package cn.com.zhanss.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈
 *
 * @author zhanss
 * @since 2022-04-29
 */
public class MonotoneStack {

    /**
     * 求数组中左右两侧比index位置小的最近的值的位置
     * arr = [3,4,2,5,6,0,1]
     * 时间复杂度O(n)
     * @param arr
     * @return res = [0]{-1,2},[1]{0,2},[2]{-1,5},[3]{2,5}...
     */
    public int[][] getNearLess(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        // arr[][0]二维数组的0位置表示左侧比i位置小的，1位置表示右边比i位置小的
        int[][] res = new int[arr.length][2];
        // 栈中存放的是从栈底到栈顶，数组从小到大排列，值相等时放list中
        Stack<List<Integer>> stack = new Stack<>();
        int i = 0;
        while (i < arr.length) {
            if (stack.isEmpty()) {
                List<Integer> pop = new ArrayList<>();
                pop.add(i);
                stack.push(pop);
            } else if (arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> pop = stack.pop();
                int moreIndex = stack.isEmpty() ? -1 : stack.peek().get(0);
                for (Integer popIndex : pop) {
                    res[popIndex][0] = moreIndex;
                    res[popIndex][1] = i;
                }
            } else {
                // 栈顶元素大于等于arr[i]
                stack.peek().add(i);
            }
            i ++;
        }
        // 栈中剩余元素弹出并统计
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            int moreIndex = stack.isEmpty() ? -1 : stack.peek().get(0);
            for (Integer popIndex : pop) {
                res[popIndex][0] = moreIndex;
                res[popIndex][1] = i;
            }
        }
        return res;
    }

}
