package cn.com.zhanss.datastructure.doexercise;

import org.junit.Test;

import java.util.*;

/**
 * 字符串算法
 *
 * @author zhanss
 * @since 2022-05-02
 */
public class StrExercise {

    @Test
    public void test() {
        String str = "ohomm";
        int max = lengthOfLongestSubstring(str);
        System.out.println(max);
    }

    /**
     * 给定一个字符串s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: s = "abcabcbb"
     * 输出: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLength = 0;
        Set<Character> ches = new HashSet<>();
        char[] chars = s.toCharArray();
        int i, j;
        for (i = 0; i < chars.length;) {
            j = i;
            while (j < chars.length) {
                if (ches.add(chars[j])) {
                    j ++;
                } else {
                    maxLength = Math.max(maxLength, ches.size());
                    // new一个新对象ches，比ches.clear()效率高
                    ches = new HashSet<>();
                    i ++;
                    break;
                }
            }
        }
        return maxLength;
    }

}
