package cn.com.zhanss.javaee.juc;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 测试阻塞IO
 *
 * @author zhanss
 * @since 2019/10/1
 */
public class TestBlockingIO {

    @Test
    public void client() throws IOException {
        // 1、获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8989));

        FileChannel fileChannel = FileChannel.open(Paths.get("d:/1.jpg"), StandardOpenOption.READ);

        // 获取缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 文件通道读取到缓冲区
        // 接收反馈信息
        int len = 0;
        while ((len = fileChannel.read(byteBuffer)) != -1) {
            // 缓冲区切换到写模式
            System.out.println(new String(byteBuffer.array(), 0, len));
            byteBuffer.flip();
            // 通过网络通道将缓冲区文件写入物理内存
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        // 关闭并输出反馈信息
        socketChannel.shutdownOutput();

        // 关闭通道
        fileChannel.close();
        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
        // 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(8989));

        // 接收远程链接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 获取缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 获取本地通道
        FileChannel fileChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        while (socketChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        // 发送反馈信息
        byteBuffer.put("服务端接收完毕".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);

        socketChannel.close();
        serverSocketChannel.close();
    }
}
