package cn.com.zhanss.structure.rbt;

import org.junit.Test;

import java.util.TreeMap;

/**
 * 测试红黑树
 *
 * @author zhanss
 * @since 2019/10/28
 */
public class TestRBT {

    @Test
    public void testRBTInvertion() throws Exception {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int[] arrs = {14, 12, 23, 6, 8, 90, 67};
        for (int arr : arrs) {
            map.put(arr, arr);
        }
        ReflectUtilForTreemap.levelOrderPrintTree(map);
    }

    @Test
    public void testRemove() {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int[] array = {12, 1, 9, 2, 0, 11, 7, 19, 4, 15, 18, 5, 14, 13, 10, 16, 6, 3, 8, 17};
        for (int key : array) {
            map.put(key, key);
        }
        // successor --> fix(p) | rightCase4-2 | p = null
        Integer key12 = 12;
        map.remove(key12);

        // successor --> replacement --> p = null --> fix(replacement) | redOver
        Integer key1 = 1;
        map.remove(key1);

        // successor --> fix(p) | leftCase2-2 --> redOver | p = null
        Integer key9 = 9;
        map.remove(key9);

        // successor --> fix(p) | rightCase2-2 --> redOver | p = null
        Integer key2 = 2;
        map.remove(key2);

        // p = null
        Integer key0 = 0;
        map.remove(key0);

        // replacement --> p = null --> fix(replacement) | redOver
        Integer key11 = 11;
        map.remove(key11);

        // replacement --> p = null --> fix(replacement) | redOver
        Integer key7 = 7;
        map.remove(key7);

        // fix(p) | rightCase4-1 | p = null
        Integer key19 = 19;
        map.remove(key19);

        // successor --> fix(p) | leftCase2-2 --> redOver | p = null
        Integer key4 = 4;
        map.remove(key4);

        // fix(p) | leftCase3 --> leftCase4-2 | p = null
        System.out.println("fix(p) | leftCase3 --> leftCase4-2 | p = null");
        Integer key15 = 15;
        map.remove(key15);

        // fix(p) | rightCase2-2 --> redOver | p = null
        Integer key18 = 18;
        map.remove(key18);

        // successor --> replacement --> p = null --> fix(replacement) | redOver
        Integer key5 = 5;
        map.remove(key5);

        // successor --> p = null
        System.out.println("successor --> p = null");
        Integer key14 = 14;
        map.remove(key14);

        // fix(p) | leftCase2-2 --> rightCase2-1 --> redOver | p = null blackHeight = 2
        System.out.println("fix(p) | leftCase2-2 --> rightCase2-1 --> redOver | p = null blackHeight = 2");
        Integer key13 = 13;
        map.remove(key13);

        // successor --> replacement --> p = null --> fix(replacement) | redOver
        Integer key10 = 10;
        map.remove(key10);

        // successor --> fix(p) | leftCase1 --> rightCase2-2 --> redOver | p = null
        Integer key16 = 16;
        map.remove(key16);

        // successor --> p = null
        System.out.println("successor --> p = null");
        Integer key6 = 6;
        map.remove(key6);
    }

}
