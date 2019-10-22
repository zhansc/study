package cn.com.zhanss.queue.interview;

import org.junit.Test;

import java.util.*;

/**
 * 测试集合类
 *
 * @author zhanss
 * @since 2019/9/25
 */
public class TestCollect {

    private static HashMap<Integer, Integer> map = new HashMap<>(2, 0.75f);

    public static void main(String[] args) {
        map.put(5, 55);

        new Thread("Thread1") {
            public void run() {
                map.put(7, 77);
                System.out.println(map);
            }
        }.start();
        new Thread("Thread2") {
            public void run() {
                map.put(3, 33);
                System.out.println(map);

            }
        }.start();
    }

    @Test
    public void test1() {
        Map<String, Integer> us = new HashMap<>();
        us.put("f1", 12);
        us.put("f2", 13);
        us.put("f5", 22);
        us.put("f4", 42);
        us.put("f3", 15);
        us.put("f8", 21);
        us.put("f6", 123);
        us.put("f7", 1);
        us.put("f9", 19);
        System.out.println(us.toString());
        System.out.println(new TreeMap<>(us));
    }

    @Test
    public void test2() {
        Map<String, Integer> us = new HashMap<>();
        us.put("f1", 12);
        us.put("f2", 13);
        us.put("f5", 22);
        us.put("f4", 42);
        us.put("f3", 15);
        us.put("f8", 21);
        us.put("f6", 123);
        us.put("f7", 1);
        us.put("f9", 19);
        Set<Map.Entry<String, Integer>> ks = us.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(ks);
        list.sort((o1, o2) -> {
            if (o1.getValue() < o2.getValue())
                return -1;
            else if (o1.getValue() > o2.getValue())
                return 1;
            return 0;
        });
        System.out.println("根据value排序："+ list);
    }

}

