package cn.com.zhanss.datastructure.tree.bstAVL;

import org.junit.Test;

import java.util.Random;
import java.util.TreeMap;

/**
 * @author zhanss
 * @since 2019/10/23
 */
public class TestBSTVSTreeMap {

    private Random random = new Random();

    private static final int MAX = 65536;

    @Test
    public void testBSTRandom() {
        AVLMap<Integer, String> avlMap = new AVLMap<Integer, String>();
        for (int i = 0; i < MAX; i ++) {
            avlMap.put(random.nextInt(MAX), random.nextInt(MAX)+ "");
        }
        // 检测是否是平衡树
        avlMap.checkBalance();
        for (int i = 0; i < MAX; i ++) {
            avlMap.get(random.nextInt(MAX));
        }
    }

    @Test
    public void testTreeRandom() {
        TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
        for (int i = 0; i < MAX; i ++) {
            treeMap.put(random.nextInt(MAX), random.nextInt(MAX)+ "");
        }
        for (int i = 0; i < MAX; i ++) {
            treeMap.get(random.nextInt(MAX));
        }
    }

    @Test
    public void testBSTIncrease() {
        AVLMap<Integer, String> avlMap = new AVLMap<Integer, String>();
        for (int i = 0; i < MAX; i ++) {
            avlMap.put(i, random.nextInt(MAX)+ "");
        }
        avlMap.checkBalance();
        for (int i = 0; i < MAX; i ++) {
            avlMap.get(random.nextInt(MAX));
        }
    }

    @Test
    public void testTreeIncrease() {
        TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
        for (int i = 0; i < MAX; i ++) {
            treeMap.put(i, random.nextInt(MAX)+ "");
        }
    }
}
