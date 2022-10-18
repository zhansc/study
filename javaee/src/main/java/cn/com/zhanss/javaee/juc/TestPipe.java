package cn.com.zhanss.javaee.juc;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 测试管道
 *
 * @author zhanss
 * @since 2019/10/2
 */
public class TestPipe {

    @Test
    public void pipe() throws IOException {
        // 获取管道
        Pipe pipe = Pipe.open();

        // 获取发送通道
        Pipe.SinkChannel sinkChannel = pipe.sink();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put("管道测试".getBytes());
        byteBuffer.flip();
        sinkChannel.write(byteBuffer);

        // 获取接收通道
        Pipe.SourceChannel sourceChannel = pipe.source();

        byteBuffer.flip();
        int len = sourceChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(), 0, len));

        sinkChannel.close();
        sourceChannel.close();

    }
}
