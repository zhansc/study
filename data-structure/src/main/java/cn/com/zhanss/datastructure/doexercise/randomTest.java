package cn.com.zhanss.datastructure.doexercise;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @desc 随机数
 * @author zhanshuchan
 * @date 2022/2/17
 */
public class randomTest {

    @Test
    public void testBigDecimal() {
        BigDecimal bd = new BigDecimal(10.01);
        System.out.println("bd---->"+ bd);
        System.out.println("bd.long---->"+ bd.longValue());
//        System.out.println("bd.int---->"+ bd.intValue());
        long bdl = bd.longValue();
        long result = bdl * 100;
        System.out.println(result);

        try {
            BigDecimal bigDecimal = new BigDecimal("0");
            Long amountInner = bigDecimal.multiply(new BigDecimal(100)).longValue();
            System.out.println("amountInner1--->"+ String.valueOf(bigDecimal));
            if (amountInner < 0) {
                amountInner = Math.abs(amountInner);
            }
            System.out.println("amountInner2--->"+ bigDecimal);
            System.out.println("amountInnerStr--->"+ String.valueOf(amountInner));
        } catch (Exception e) {
            System.out.println("amountInnerStr--->"+ e);
        }
    }
}
