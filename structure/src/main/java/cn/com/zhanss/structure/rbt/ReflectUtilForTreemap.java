package cn.com.zhanss.structure.rbt;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 通过反射获取TreeMap中的私有参数：反射破坏了java的封装特性
 *
 * @author zhanss
 * @since 2019/10/28
 */
public class ReflectUtilForTreemap {
    /**
     * 将TreeMap中私有的参数共有化
     */
    public static Class<?> entryClass;

    public static Field leftField;

    public static Field rightField;

    public static Class<?> treeMapClass;

    public static Field rootField;

    public static Field colorField;

    static {
        try {
            entryClass = Class.forName("java.util.TreeMap$Entry");
            leftField = entryClass.getDeclaredField("left");
            leftField.setAccessible(true);
            rightField = entryClass.getDeclaredField("right");
            rightField.setAccessible(true);
            treeMapClass = TreeMap.class;
            rootField = treeMapClass.getDeclaredField("root");
            rootField.setAccessible(true);
            colorField = entryClass.getDeclaredField("color");
            colorField.setAccessible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static <K, V> void printTreeNode(Map.Entry<K, V> p) throws Exception {
        boolean color = colorField.getBoolean(p);
        String colorStr;
        if (color) {
            colorStr = "BLACK";
        } else {
            colorStr = "RED";
        }
        System.out.println(p.getKey() + "--"+ colorStr + "--"+ p.getValue());
    }

    /**
     * 层序输出JDK的红黑树
     * @param map
     * @param <K>
     * @param <V>
     * @throws Exception
     */
    public static <K, V> void levelOrderPrintTree(TreeMap<K, V> map) throws Exception {
        Map.Entry<K, V> root = (Map.Entry<K, V>) rootField.get(map);
        if (root == null) {
            return;
        }
        Queue<Map.Entry<K, V>> queue = new LinkedList<Map.Entry<K, V>>();
        queue.offer(root);
        int preCount = 1;
        int pCount = 0;
        while (!queue.isEmpty()) {
            Map.Entry<K, V> p = queue.poll();
            preCount --;
            printTreeNode(p);
            if (leftField.get(p) != null) {
                queue.offer((Map.Entry<K, V>) leftField.get(p));
                pCount ++;
            }
            if (preCount == 0) {
                preCount = pCount;
                pCount = 0;
            }
        }
        System.out.println("----------------------");
    }
}
