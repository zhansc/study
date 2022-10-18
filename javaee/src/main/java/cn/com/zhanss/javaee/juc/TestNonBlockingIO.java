package cn.com.zhanss.javaee.juc;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 测试非阻塞IO
 *
 * @author zhanss
 * @since 2019/10/2
 */
public class TestNonBlockingIO {

    @Test
    public void client() throws IOException {
        // 获取网络通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8181));
        // 设置为非阻塞IO
        socketChannel.configureBlocking(false);
        // 获取缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 写数据
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        while (scanner.hasNext()) {
            // 写入缓冲区
            byteBuffer.put((sdf.format(new Date())+ "\n" + scanner.nextLine()).getBytes());
            byteBuffer.flip();
            // 通过远程通道传到服务端
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
        // 获取远程通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置为非阻塞通道
        serverSocketChannel.configureBlocking(false);
        // 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(8181));
        // 获取选择器
        Selector selector = Selector.open();
        // 将远程通道注册到选择器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 获取注册器上所有"准备就绪"的事件
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    // 若是“连接就绪”事件，接收连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    // 将本地通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (next.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) next.channel();
                    // 获取缓冲区
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = socketChannel.read(byteBuffer)) != -1) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                }
                // 取消所有的选择器键SelecttionKey
                iterator.remove();
            }
        }
    }

    @Test
    public void test() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        System.out.println(scanner.nextLine());
    }

}
