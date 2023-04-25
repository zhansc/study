package cn.com.zhanss.datastructure.doexercise.number;

import org.junit.Test;

public class Test1 {
    @Test
    public void test1() {
        int ran = (int) (Math.random() * 150);
        System.out.println(ran);
        int evenMultiple = new Solution().longestContinuousSubstring("abacaba");
        System.out.println(evenMultiple);
    }

    /**
     * 给你一个正整数 n ，返回 2 和 n 的最小公倍数（正整数）。
     * https://leetcode.cn/problems/smallest-even-multiple/
     */
    class Solution {
        public int smallestEvenMultiple(int n) {
            if (n < 1 || n > 150) {
                try {
                    throw new Exception("请输入指定范围内数据");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (n % 2 == 0) {
                return n;
            }
            return 2 * n;
        }

        public int smallestEvenMultiple2(int n) {
            return n << (n & 1);
        }

        public int longestContinuousSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int chLength = s.length();
            if (chLength == 1) {
                return 1;
            }
            int max = 1;
            int subLength = 1;
            char[] chars = s.toCharArray();
            while(-- chLength > 0) {
                if (chars[chLength] - chars[chLength - 1] == 1) {
                    subLength ++;
                } else {
                    max = Math.max(max, subLength);
                    subLength = 1;
                }
                // 提前退出
                if (subLength == 26) {
                    break;
                }
            }
            return Math.max(max, subLength);
        }

    }
}
