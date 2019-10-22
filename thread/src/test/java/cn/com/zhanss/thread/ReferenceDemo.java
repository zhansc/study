package cn.com.zhanss.thread;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 强、软、若、虚引用
 * 强引用都不会回收
 * 软引用在内存够的时候不回收，内存不够的时候会被GC回收
 * 若引用只要有GC动作都会被回收
 *
 * @author zhanss
 * @since 2019/9/20
 */
public class ReferenceDemo {

    // 内存充足
    public static void SoftRef_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);

        System.out.println("object:"+ o1);
        o1 = null;
        System.out.println("object:"+ o1);
        System.out.println("softReference:"+ softReference.get());

    }

    // 内存不足
    public static void SoftRef_NotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        o1 = null;
        System.out.println("====object===:"+ o1);
        System.out.println("====softReference=====:"+ softReference.get());
        try {
            String[] str = new String[10 * 1024* 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("=====softReference====:"+ softReference.get());
        }
    }

    public static void main(String[] args) {
        myMap();

    }

    private static void myMap() {
        //        SoftRef_Enough();
//        SoftRef_NotEnough();

        // WeakHashMap
        Map<String, Object> map = new HashMap<>();
        map.put(null, null);
        String key = "key";
        Object value = "hashmap";
        map.put(key, value);

        System.out.println(map);
        key = null;
        System.out.println(map);
    }
}
