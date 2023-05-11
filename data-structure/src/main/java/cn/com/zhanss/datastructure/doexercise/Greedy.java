package cn.com.zhanss.datastructure.doexercise;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;

/**
 * 贪心算法
 *
 * @author zhanss
 * @since 2022-04-25
 */
public class Greedy {

    /**
     * 给定一个字符串str，只由‘X’和‘.’两种字符构成。‘X’表示墙，不能放灯，也不需要照亮，‘.’表示居民点，可以放灯，需要被照亮。
     * 如果灯放在i位置，可以让i - 1，i 和 i + 1三个位置被照亮。返回如果照亮str中所有需要照亮的位置，至少需要放置几盏灯？
     * 暴力解法
     * @param road
     * @return
     */
    public static Integer minLights(String road) {
        if (StringUtils.isEmpty(road)) {
            // 表示没有放灯
            return Integer.MAX_VALUE;
        }
        char[] chars = road.toCharArray();
        return minLightsProcess(chars, 0, new HashSet<>());
    }

    public static void main(String[] args) {
        Integer integer = minLightsGreedy("..XXX....X.X..X");
        System.out.println(integer);
    }
    private static Integer minLightsProcess(char[] chars, Integer index, HashSet<Integer> lights) {
        if (index == chars.length) {
            // 结束后，校验下
            for (int i = 0; i < chars.length; i ++) {
                if (chars[i] == '.' && (!lights.contains(i - 1) || lights.contains(i) || lights.contains(i + 1))) {
                    // 表示没有放灯
                    return Integer.MAX_VALUE;
                }
            }
            return lights.size();
        } else {
            // 不放灯
            int no = minLightsProcess(chars, index + 1, lights);
            // 放灯
            int yes = Integer.MAX_VALUE;
            if (chars[index] == '.') {
                lights.add(index);
                yes = minLightsProcess(chars, index + 1, lights);
                // 恢复放灯之前的现场
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    /**
     * 给定一个字符串str，只由‘X’和‘.’两种字符构成。‘X’表示墙，不能放灯，也不需要照亮，‘.’表示居民点，可以放灯，需要被照亮。
     * 如果灯放在i位置，可以让i - 1，i 和 i + 1三个位置被照亮。返回如果照亮str中所有需要照亮的位置，至少需要放置几盏灯？
     * 贪心解法
     * @param road
     * @return
     */
    public static Integer minLightsGreedy(String road) {
        if (StringUtils.isEmpty(road)) {
            // 表示没有放灯
            return -1;
        }
        char[] chars = road.toCharArray();
        int index = 0;
        int lights = 0;
        while (index < chars.length) {
            if (chars[index] == 'X') {
                // 不放灯
                index ++;
            } else {
                // i+1位置一定放灯
                lights ++;
                int next = index + 1;
                if (next < chars.length && chars[next] == 'X') {
                    // i位置放灯，然后继续判断i+2位置
                    index += 2;
                } else {
                    // i+1位置是点.，不管i+2位置是什么都要在i+1位置放灯，然后继续判断i+3位置
                    index += 3;
                }
            }
        }
        return lights;
    }

}
