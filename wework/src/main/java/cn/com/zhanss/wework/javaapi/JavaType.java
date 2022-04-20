package cn.com.zhanss.wework.javaapi;

import org.junit.Test;

/**
 * java的基础类型
 *
 * @author zhanss
 * @since 2022-04-18
 */
public class JavaType {

    @Test
    public void testShort() {
        /**
         * short、byte类型的数据之间的计算，是先转为int类型
         * 然后将计算结果转为对应类型
         */
        short s1 = 1;
        short s2 = 2;
        // s2 = s1 + s1;
        // s1 = s1 + 1;
        s1 += 1;

        byte b1 = 1;
        b1 += 1;
    }

}
