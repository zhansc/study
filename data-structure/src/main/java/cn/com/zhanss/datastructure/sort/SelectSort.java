package cn.com.zhanss.datastructure.sort;

import lombok.Data;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * 选择排序
 *
 * @author zhanss
 * @since 2020/2/11
 */
public class SelectSort {

    @Test
    public void main() {
        int[] arr = {2, 11, 9, 23, 5, 1, 4, -1, 3, -12, 34, 11, 0, -182};
        select(arr);
        System.out.println("arr=="+ Arrays.toString(arr));

        Map<String, List<String>> map1 = new HashMap<>();
        Map<String, List<String>> map11 = new HashMap<>();
        Map<String, List<String>> map2 = new HashMap<>();
        Map<String, List<String>> map3 = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("math");
        list1.add("Chinese");
        list1.add("English");
        list1.add("Phsisc");

        list2.add("a");
        list2.add("b");
        list2.add("c");

        map1.put("subject", list1);
        map11.put("subject", list1);
        map3.putAll(map11);
        System.out.println("map3"+ map3);
        map11.put("subject", list2);
        map2.put("abc", list2);
        map3.putAll(map11);
        System.out.println("map3"+ map3);

        Person person = new Person();
//        person.setRules(list1);
        person.setId(1);
        person.setName("zsc");
        System.out.println("person"+ person);

        int num = 0;
        int n = 4;
        for (int i = 0; i < n; i ++) {
            num += (i + n);
        }
        System.out.println("num="+ num);
    }

    /**
     * 算法：先简单 --> 再复杂，算法就是将复杂问题拆解成一个个小的简单的问题
     * 选择排序 比冒泡排序 更快
     * @param arr
     */
    private void select (int[] arr) {
        if (ObjectUtils.isEmpty(arr)) {
            return;
        }
        System.out.println(Arrays.toString(arr));
        int temp;
        // 进行n - 1 次比较
        for (int i = 0; i < arr.length - 1; i ++) {
            // 每次找出剩余部分数组中的最小数
            for (int j = i + 1; j < arr.length; j ++) {
                if (arr[i] > arr[j]) {
                   temp = arr[j];
                   arr[j] = arr[i];
                   arr[i] = temp;
               }
            }
        }
    }

}
@Data
class Person {
    private List<String> rules;

    private int id;

    private String name;

    private String sex;
}