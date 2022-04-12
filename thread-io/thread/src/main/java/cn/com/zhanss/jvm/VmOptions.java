package cn.com.zhanss.jvm;

import org.junit.Test;

/**
 * JVM参数调试
 *
 * @author zhanss
 * @since 2022-04-11
 */
public class VmOptions {

    @Test
    public void testHeapOom() {
        // 设置堆大小为10m  --> -Xmx10m -XX:+PrintGCDetails
        // 测试heap内存溢出
        String[] strs = new String[11*1000*1000];
        System.out.println("124678");
    }

}
