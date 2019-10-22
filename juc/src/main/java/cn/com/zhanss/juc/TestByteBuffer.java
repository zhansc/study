package cn.com.zhanss.juc;

import java.nio.ByteBuffer;

/**
 * 测试Byte缓冲区
 *
 * @author zhanss
 * @since 2019/9/30
 */
public class TestByteBuffer {

    public static void main(String[] args) {
        String str = "abcdef";
        testMethod(str);

    }

    private static void testMethod(String str) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // put 存入缓冲区
        byteBuffer.put(str.getBytes());
        // position <= limit <= capacity
        // 正在操作的数据的位置
        System.out.println("=========put=========");
        System.out.println(byteBuffer.position());
        // 缓冲区中可以操作的数据的大小，limit后面的数据不能读写
        System.out.println(byteBuffer.limit());
        // 缓冲区容量
        System.out.println(byteBuffer.capacity());

        // 从写模式切换到读模式
        byteBuffer.flip();
        // 切换之后：position变为0，limit变为切换前的position，capacity不变
        System.out.println("=========get=========");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        byte[] bytes = new byte[byteBuffer.capacity()];
        // 读超过limit的数据会提示数组越界
        byteBuffer.get(bytes, 0, 6);
        System.out.println(new String(bytes));
        System.out.println(byteBuffer.position());
        // 此时的limit是6，直接进入写模式，写入超过6的大小的数据会提示数组越界
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println("========clear=========");
        // 清空缓冲区(进入写模式)，但是数据并没有删除，只是position、limit、mark重置
        byteBuffer.clear();
        // 所以在进入读模式，再想进入写模式需要reset，重置参数：position，limit，capacity
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("========re=put========="+ bytes.length);
        byteBuffer.put("Hello".getBytes(), 0, 5);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        byteBuffer.get(bytes, 0, byteBuffer.position());
        System.out.println(new String(bytes));
    }
}
