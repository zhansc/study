package cn.com.zhanss.juc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 测试通道
 *
 * @author zhanss
 * @since 2019/9/30
 */
public class TestChannel {

    public static void main(String[] args) throws IOException {

//        test1();
        // 测试通道channel
//        test2();
        // 分散读取与聚集写入
//        test3();
        // 字符集
        testCharset();
    }

    private static void testCharset() throws CharacterCodingException {

        Charset gbk = Charset.forName("GBK");
        // 获取编码器
        CharsetEncoder charsetEncoder = gbk.newEncoder();
        // 获取解码器
        CharsetDecoder charsetDecoder = gbk.newDecoder();

        CharBuffer cb = CharBuffer.allocate(1024);
        cb.put("zhanss");
        cb.flip();
        // 编码
        ByteBuffer encode = charsetEncoder.encode(cb);

        // 解码
        encode.flip();
        CharBuffer decode = charsetDecoder.decode(encode);
        System.out.println(decode.get());
    }

    private static void test3() {
    }

    private static void test2() throws IOException {

        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"),
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);

        // 从inChannel缓冲区写到outChannel
        // inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
    }

    private static void test1() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"),
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);

        // 内存映射文件：map(映射方式, 开始位置, 大小)
        MappedByteBuffer inMapperBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapperBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        System.out.println("复制..."+ inMapperBuf.position());
        // 直接从缓冲区中读写数据
        byte[] dst = new byte[inMapperBuf.limit()];
        inMapperBuf.get(dst);
        outMapperBuf.put(dst);
        System.out.println("复制成功"+ inMapperBuf.position());
        inChannel.close();
        outChannel.close();
    }

}
