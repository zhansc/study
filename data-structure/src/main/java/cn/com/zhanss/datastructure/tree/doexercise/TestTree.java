package cn.com.zhanss.datastructure.tree.doexercise;

import cn.com.zhanss.datastructure.tree.Node;
import cn.com.zhanss.datastructure.tree.rbt.TreeBuilder;
import org.junit.Test;

import java.util.*;

public class TestTree {

    /**
     * 给你一棵 完美 二叉树的根节点 root ，请你反转这棵树中每个 奇数 层的节点值。
     * <p>
     * 例如，假设第 3 层的节点值是 [2,1,3,4,7,11,29,18] ，那么反转后它应该变成 [18,29,11,7,4,3,1,2] 。
     * 反转后，返回树的根节点。
     * <p>
     * 完美 二叉树需满足：二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层。
     * <p>
     * 节点的 层数 等于该节点到根节点之间的边数。
     * <p>
     * 链接：https://leetcode.cn/problems/reverse-odd-levels-of-binary-tree
     */

    @Test
    public void test() {

        Node node = TreeBuilder.generateRandomBST(3, 39);
        Node Node = new Solution().reverseOddLevel(node);
        System.out.println("end");
    }

    class Solution {
        public Node reverseOddLevel(Node root) {
            reverse(root.left(), root.right(), true);
            return root;
        }

        /**
         * 异或完成两数交换
         * @param l
         * @param r
         */
        void swap(Node l, Node r) {
            int lv = l.getValue();
            int rv = r.getValue();
            lv = lv ^ rv;
            rv = lv ^ rv;
            lv = lv ^ rv;

            l.setValue(lv);
            r.setValue(rv);
        }

        void reverse(Node l, Node r, boolean flag) {
            if (l == null) return;
            if (flag) swap(l, r);
            reverse(l.left(), r.right(), !flag);
            reverse(l.right(), r.left(), !flag);
        }

        // 1、将奇数层的节点值交换一下
        // 2、将奇数层节点交换
        public Node reverseOddLevels(Node root) {
            if (root == null || root.left() == null || root.right() == null) {
                return root;
            }
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(root);
            Map<Node, Integer> curLevel = new HashMap<>();
            curLevel.put(root, 0);
            Map<Integer, List<Node>> levelNodes = new HashMap<>();
            Map<Integer, Stack<Integer>> stackNodes = new HashMap<>();
            Node temp;
            while (!queue.isEmpty()) {
                temp = queue.pop();
                Integer nextLevel = curLevel.get(temp) + 1;
                boolean odd = nextLevel % 2 > 0;
                if (temp.left() != null) {
                    if (odd) {
                        if (levelNodes.containsKey(nextLevel)) {
                            levelNodes.get(nextLevel).add(temp.left());
                            stackNodes.get(nextLevel).add(temp.left().getValue());
                        } else {
                            ArrayList<Node> oddNodes = new ArrayList<>();
                            oddNodes.add(temp.left());
                            levelNodes.put(nextLevel, oddNodes);
                            Stack<Integer> stack = new Stack<>();
                            stack.add(temp.left().getValue());
                            stackNodes.put(nextLevel, stack);
                        }
                    }
                    curLevel.put(temp.left(), nextLevel);
                    queue.add(temp.left());
                }
                if (temp.right() != null) {
                    if (odd) {
                        if (levelNodes.containsKey(nextLevel)) {
                            levelNodes.get(nextLevel).add(temp.right());
                            stackNodes.get(nextLevel).add(temp.right().getValue());
                        } else {
                            ArrayList<Node> oddNodes = new ArrayList<>();
                            oddNodes.add(temp.right());
                            levelNodes.put(nextLevel, oddNodes);
                            Stack<Integer> stack = new Stack<>();
                            stack.add(temp.right().getValue());
                            stackNodes.put(nextLevel, stack);
                        }
                    }
                    curLevel.put(temp.right(), nextLevel);
                    queue.add(temp.right());
                }
            }
            for (Map.Entry<Integer, List<Node>> Nodes : levelNodes.entrySet()) {
                if (Nodes.getKey() % 2 > 0) {
                    List<Node> nodes = Nodes.getValue();
                    Stack<Integer> stack = stackNodes.get(Nodes.getKey());
                    for (Node node : nodes) {
                        node.setValue(stack.pop());
                    }
                }
            }
            return root;
        }
    }

}
