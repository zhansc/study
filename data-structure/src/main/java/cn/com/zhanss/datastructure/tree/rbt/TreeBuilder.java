package cn.com.zhanss.datastructure.tree.rbt;

import cn.com.zhanss.datastructure.tree.Node;

/**
 * 构建一棵树
 *
 * @author zhanss
 * @since 2022-04-25
 */
public class TreeBuilder extends Node {

    public TreeBuilder(Integer value) {
        super(value);
    }

    /**
     * 随机生成一个二叉树
     * @param maxLevel
     * @param maxValue
     * @return
     */
    public static Node generateRandomBST(Integer maxLevel, Integer maxValue) {
        return generateRandom(1, maxLevel, maxValue);
    }

    private static Node generateRandom(Integer level, Integer maxLevel, Integer maxValue) {
        if (level > maxLevel || maxLevel < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.setLeft(generateRandom(level + 1, maxLevel, maxValue));
        head.setRight(generateRandom(level + 1, maxLevel, maxValue));
        return head;
    }
}
