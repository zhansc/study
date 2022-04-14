package cn.com.zhanss.datastructure.sort;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * 自定义BitMap
 *
 * @author zhanss
 * @since 2022-04-13
 */
@Data
@NoArgsConstructor
public class MyBitMap {

    private int[][] bit = new int[8][8];

    public boolean put(int val) {
        int bytePos = val / Integer.MAX_VALUE;
        int byteIndex = val % Integer.MAX_VALUE;
        bit[bytePos][byteIndex] = 1;
        return true;
    }

    public boolean remove(int val) {
        int bytePos = val / Integer.MAX_VALUE;
        int byteIndex = val % Integer.MAX_VALUE;
        bit[bytePos][byteIndex] = 0;
        return true;
    }

    @Test
    public void test() {
        MyBitMap bitMap = new MyBitMap();
        int[] arr = new int[]{1,3,4,6};
        for (int i = 0; i < arr.length; i ++) {
            bitMap.put(arr[i]);
        }
        bitMap.remove(3);
        System.out.println("end");
    }
}
