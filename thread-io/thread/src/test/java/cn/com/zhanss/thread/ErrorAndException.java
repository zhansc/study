package cn.com.zhanss.thread;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 异常和错误
 *
 * @author zhanss
 * @since 2019/9/20
 */
public class ErrorAndException {

    public static void main(String[] args) {
        // GC大力量的回收，但效率极低导致错误
//        GCOverHeeadLimit();

        // NIO时分配的本地直接内存消耗完了，而JVM堆内存没怎么用，导致directMemory移除错误
        directMemory();

//        UnableCreateNativeThread();

//        metaSpaceError(args);
    }

    /**
     * JVM参数配置：
     * -XX:+PrintGCDetails -Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m
     */
    private static void GCOverHeeadLimit() {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("==========i:"+ i);
        }
    }

    /**
     * ByteBuffer.allocate(capacity) 第一种方式是分配在Java堆内存，属于GC管辖，由于需要在Java堆和Native堆间复制数据，速度相对慢些；
     * ByteBuffer.allocateDirect(capacity)第二种方式是分配在系统本地内存中，不属于GC管辖，不需要来回拷贝数据，所以速度相对较快；
     * 但如果本地内存分配很少，使用很多，DirectByteBuffer又不会被GC回收，
     * 可能会导致java.java.lang.OutOfMemoryError: Direct buffer memory
     */
    private static void directMemory(){
        System.out.println("配置的maxDirectMemory："+ (sun.misc.VM.maxDirectMemory() /(double)1024 / 1024) + "MB");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
    }

    private static void UnableCreateNativeThread() {
        int i = 0;
        try {
            for (; ;i ++) {
                new Thread().start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("第几个溢出了："+ i);
        }
    }

    // 内部静态类
    static class metaSpace {}

//    private static void metaSpaceError(String[] args) {
//        int i = 0;
//        try {
//            while (true) {
//                i ++;
//                Enhancer enhancer = new Enhancer();
//                enhancer.setSuperclass(metaSpace.class);
//                enhancer.setUseCache(false);
//                enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, args));
//                enhancer.create();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("第几个溢出了："+ i);
//        }
//    }

}
