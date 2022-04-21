package cn.com.zhanss.datastructure.map;

import org.junit.Test;

/**
 * 自定义HashMap测试
 *
 * @author zhanss
 * @since 2022-04-20
 */
public class CustomHashMapTest {

    @Test
    public void test1() {
        CustomHashMap<String, Integer> custom = new CustomHashMap<>(5);
        custom.put("a", 1);
        custom.put("b", 2);
        custom.put("c", 3);
        custom.put("d", 4);
        custom.put("e", 5);
        custom.put("f", 6);
        custom.put("g", 7);
        custom.put("i", 8);
        custom.put("j", 9);

        System.out.println(custom.get("D"));
        System.out.println(custom.size());

        CustomHashMap<String, String> custom1 = new CustomHashMap<>();
        custom1.put("语文", "123");
        custom1.put("英语", "112");
        System.out.println(custom1.isEmpty());
        System.out.println(custom1.containsKey("语文"));
        System.out.println(custom1.containsKey("数学"));

    }
}
