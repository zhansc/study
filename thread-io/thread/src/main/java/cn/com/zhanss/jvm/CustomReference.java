package cn.com.zhanss.jvm;

import java.lang.ref.SoftReference;
import java.util.WeakHashMap;

/**
 * 引用
 *
 * @author zhanss
 * @since 2022-04-11
 */
public class CustomReference {

    public static void testSoftReference() {
        String str = "软引用";
        SoftReference<String> softReference = new SoftReference<>(str);
        System.out.println("打印下-->"+softReference.get());

        try {
            // Vm options = -Xmx10m
            String[] strs = new String[11*1024*1024];
        } catch (Exception e) {

        } finally {
            System.out.println("打印下下-->"+ softReference.get());
        }
    }

    public static void testWeakReference() {
        WeakHashMap<String, Integer> weakHashMap = new WeakHashMap<>();
        weakHashMap.put("语文", 123);
        weakHashMap.put("数学", 113);
        weakHashMap.put("英语", 124);
        System.out.println(weakHashMap.get("语文"));

//        String[] strs = new String[10*1024*1023];
        System.gc();
        System.out.println(weakHashMap.get("语文"));

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(weakHashMap.get("语文"));
    }

    public static void main(String[] args) {
        testSoftReference();
    }
}
