package cn.com.zhanss.datastructure.str;
import java.util.HashSet;

/**
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度
 * 输入: s = "abcabcbb"输出: 3
 * @author zhanshuchan
 * @date 2025/11/5
 **/
public class StrTest {

    public static void main(String[] args) {
        String str = "abcdssdcf";
        char[] chars = str.toCharArray();
        System.out.println("字符串str= "+ str + "\n，其中不含有重复字符的最长子串的长度=" + calculate(chars));
    }

    public static int calculate(char[] arr) {
        if (arr == null) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        HashSet<Character> hash;
        int lastMax = 1;
        int max = 1;
        for (int i = 0; i < arr.length - 1; i ++) {
            hash = new HashSet<>();
            for (int j = i + 1; j < arr.length; j ++) {
                if (arr[i] == arr[j] || hash.contains(arr[j])) {
                    if (max > lastMax) {
                        lastMax = max;
                    }
                    max = 0;
                    break;
                } else {
                    hash.add(arr[j]);
                    max ++;
                }
            }
        }
        return lastMax;
    }

}
