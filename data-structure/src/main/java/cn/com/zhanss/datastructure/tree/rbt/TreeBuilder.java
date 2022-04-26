package cn.com.zhanss.datastructure.tree.rbt;

/**
 * 构建一棵树
 *
 * @author zhanss
 * @since 2022-04-25
 */
public class TreeBuilder {

    public static class Node {
        private Integer value;

        private Node left;

        private  Node right;

        public Node(Integer value) {
            this.value = value;
        }
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

    private static Node generateRandom(Integer levle, Integer maxLevel, Integer maxValue) {
        if (levle > maxLevel || maxLevel < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generateRandom(levle + 1, maxLevel, maxValue);
        head.right = generateRandom(levle + 1, maxLevel, maxValue);
        return head;
    }
}
