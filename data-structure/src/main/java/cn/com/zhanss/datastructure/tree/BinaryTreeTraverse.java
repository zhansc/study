package cn.com.zhanss.datastructure.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;

/**
 * 遍历二叉树
 *
 * @author zhanss
 * @since 2022-04-04
 */
public class BinaryTreeTraverse {

    public static class Node {
        private Integer value;

        private Node left;

        private Node right;

        public Node(Integer value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    @Test
    public void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.left = node2;
        node1.right = node3;
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node2.left = node4;
        node2.right = node5;
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node3.left = node6;
//        node3.right = node7;
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        node5.left = node8;
        node6.right = node9;

        pos(node1);
        System.out.println("\n====================");
        posterior(node1);

        System.out.println("\n====================");
        floorTraverse(node1);

        int[] maxWidthWithMap = maxWidthWithMap(node1);
        System.out.println("\n===================max="+ maxWidthWithMap[0]+ " ,maxLevel="+ maxWidthWithMap[1]);

        int maxWidthWitghoutMap = maxWidthWithoutMap(node1);
        System.out.println("\n===================maxWidthWitghoutMap="+ maxWidthWitghoutMap);

        System.out.println("\n==============================");
        printTree(node1);
    }

    /**
     * 后序遍历二叉树
     * @param head
     */
    public void posterior(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        Node node;
        while (!stack.isEmpty()) {
            node = stack.peek();
            if (node.left != null && head != node.left && head != node.right) {
                stack.add(node.left);
            } else if (node.right != null && head != node.right) {
                // head在右孩子上，说明左孩子已经打印过了
                stack.add(node.right);
            } else {
                System.out.print(stack.pop().value + " ");
                // head指针是用来标记上一次打印的节点位置
                head = node;
            }
        }
    }

    /**
     * 后序遍历
     * @param head
     */
    public void pos(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        // 临时栈，做逆序的
        Stack<Node> temp = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            temp.add(head);
            if (head.left != null) {
                stack.add(head.left);
            }
            if (head.right != null) {
                stack.add(head.right);
            }
        }
        while (!temp.isEmpty()) {
            head = temp.pop();
            System.out.print(head.value + " ");
        }
    }

    /**
     * 中序遍历
     * @param head
     */
    public void in(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.add(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }

        }
    }

    /**
     * 先序遍历
     * @param head
     */
    public void pre(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.value + " ");
            if (head.right != null) {
                stack.add(head.right);
            }
            if (head.left != null) {
                stack.add(head.left);
            }
        }
    }

    /**
     * 按层遍历
     * @param head
     */
    public void floorTraverse(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value + " ");
            if (head.left != null) {
                queue.add(head.left);
            }
            if (head.right != null) {
                queue.add(head.right);
            }
        }
    }

    /**
     * 用HashMap求二叉树最大宽度及所在层
     * @param head
     * @return
     */
    public int[] maxWidthWithMap(Node head) {
        int max = 0;
        if (head == null) {
            return new int[]{max, -1};
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Map<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1;
        int maxLevel = -1;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            head = queue.poll();
            Integer curNodeLevel = levelMap.get(head);
            if (head.left != null) {
                levelMap.put(head.left, curNodeLevel + 1);
                queue.add(head.left);
            }
            if (head.right != null) {
                levelMap.put(head.right, curNodeLevel + 1);
                queue.add(head.right);
            }
            // 当前层是否等于当前节点所在层
            if (curLevel == curNodeLevel) {
                curLevelNodes ++;
            } else {
                if (curLevelNodes > max) {
                    max = curLevelNodes;
                    maxLevel = curLevel;
                }
                curLevel ++;
                curLevelNodes = 1;
            }
        }
        // 离开当前层的时候才会统计，所以最后一层需要手动处理下
        if (curLevelNodes > max) {
            max = curLevelNodes;
            maxLevel = curLevel;
        }
        return new int[]{max, maxLevel};
    }

    /**
     * 求二叉树最大宽度
     * @param head
     * @return
     */
    public int maxWidthWithoutMap(Node head) {
        int max = 0;
        if (head == null) {
            return max;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;
        Node nextEnd = null;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                nextEnd = head.left;
                queue.add(head.left);
            }
            if (head.right != null) {
                nextEnd = head.right;
                queue.add(head.right);
            }
            curLevelNodes ++;
            // 当前层是否结束
            if (curEnd == head) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    /**
     * 打印树形二叉树
     * @param head
     */
    public void printTree(Node head) {
        if (head == null) {
            return;
        }
        int[] maxWidthWithMap = maxWidthWithMap(head);
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int parentNodeIndex = (maxWidthWithMap[0] - 1) / 2 + 1;
        int lenVal = String.valueOf(head.value).length();
        int lenL = (16 - lenVal) / 2;
        int lenR = 16 - lenVal - lenL;
        HashMap<Node, Integer> levelMap = new HashMap<>();
        int curLevel = 0;
        levelMap.put(head, curLevel + 1);
        int len = 8;
        String gap = getSpace(lenL) + head.value + getSpace(lenR);
        while (!queue.isEmpty()) {
            head = queue.poll();
            int curNodeLevel = levelMap.get(head);
            if (head.left != null) {
                levelMap.put(head.left, curLevel + 1);
                queue.add(head.left);
            }
            if (head.right != null) {
                levelMap.put(head.right, curLevel + 1);
                queue.add(head.right);
            }
            if (curLevel != curNodeLevel) {
                System.out.println(getSpace(parentNodeIndex) + gap);
                parentNodeIndex = parentNodeIndex - len/2;
                curLevel ++;
                // 下一层的开始
                gap = "";
            } else {
                String value = getSpace(lenL) + head.value + getSpace(lenR);
                gap = gap + getSpace(len) + value + getSpace(len);
            }
        }
    }

    private String getSpace(int num) {
        if (num <= 0) {
            return "";
        }
        String space = " ";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i ++) {
            stringBuilder.append(space);
        }
        return stringBuilder.toString();
    }

    /**
     * 查指定节点的后继节点
     * @param node
     * @return
     */
    public ParentNode posNode(ParentNode<Integer> node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            // 指定节点右子树为空
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            // 指定节点父节点不为空，且
            ParentNode<Integer> parent = node.parent;
            while (parent != null && node == parent.right) {
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    @Test
    public void test2() {
        ParentNode<Integer> node1 = new ParentNode<>(1);
        ParentNode<Integer> node2 = new ParentNode<>(2);
        ParentNode<Integer> node3 = new ParentNode<>(3);
        node1.left = node2;
        node2.parent = node1;
        node1.right = node3;
        node3.parent = node1;
        ParentNode<Integer> node4 = new ParentNode<>(4);
        ParentNode<Integer> node5 = new ParentNode<>(5);
        node2.left = node4;
        node4.parent = node2;
        node2.right = node5;
        node5.parent = node2;
        ParentNode node6 = new ParentNode(6);
        ParentNode node7 = new ParentNode(7);
        node3.left = node6;
        node6.parent = node3;
        node3.right = node7;
        ParentNode node8 = new ParentNode(8);
        ParentNode node9 = new ParentNode(9);
        node5.left = node8;
        node6.right = node9;
        node8.parent = node5;
        node9.parent = node6;

        ParentNode posNode = posNode(node3);
        System.out.println("\n====================posNode="+ posNode);
    }

    public static class ParentNode<V> {
        private V value;

        private ParentNode left;

        private ParentNode right;

        /**
         * 指向父节点指针
         */
        private ParentNode parent;

        public ParentNode(V value) {
            this.value = value;
        }
    }

    public static int maxSubBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        return subBSTProcess(head).maxSubBSTSize;
    }

    /**
     * 两种情况：
     * 1）和头结点无关
     * 2）和头结点有关
     * @param head
     * @return
     */
    private static BSTInfo subBSTProcess(Node head) {
        if (head == null) {
            return null;
        }
        // 左子树返回的信息
        BSTInfo leftInfo = subBSTProcess(head.left);
        // 右子树返回的信息
        BSTInfo rightInfo = subBSTProcess(head.right);

        Integer maxSubBSTSize = 0;
        if (leftInfo != null) {
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo == null ? 0 : rightInfo.maxSubBSTSize);
        Boolean isAllBST = false;
        if (
                // 满足2）
                (leftInfo == null || leftInfo.isAllBST)
                &&
                (rightInfo == null || rightInfo.isAllBST)
                &&
                // 左子树都小于我，右子树都大于我
                (leftInfo == null || leftInfo.max < head.value)
                &&
                (rightInfo == null || rightInfo.min > head.value)
        ) {
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                    + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize)
                    + 1;
            isAllBST = true;
        }

        int min = leftInfo == null ? 0 : leftInfo.min;
        min = Math.min(min, rightInfo == null ? 0 : rightInfo.min);

        int max = leftInfo == null ? 0 : leftInfo.max;
        max = Math.min(max, rightInfo == null ? 0 : rightInfo.max);
        return new BSTInfo(isAllBST, maxSubBSTSize, min, max);
    }

    /**
     * 每棵子树返回的信息
     */
    @Data
    @AllArgsConstructor
    public static class BSTInfo {
        /**
         * 是否全部搜索二叉树
         */
        private Boolean isAllBST;
        /**
         * 最大搜索二叉树大小
         */
        private Integer maxSubBSTSize;
        /**
         * 左子树最小值
         */
        private Integer min;
        /**
         * 右子树最大值
         */
        private Integer max;
    }

    /**
     * a和b两个节点的最低公共祖先
     * @param head 二叉树头结点
     * @param a 二叉树上任意两个节点
     * @param b
     * @return
     */
    public static Node firstAncestor(Node head, Node a, Node b) {
        if (head == null) {
            return null;
        }
        return firstAncestorProcess(head, a, b).ancestor;
    }

    private static FirstAncestorInfo firstAncestorProcess(Node head, Node a, Node b) {
        if (head == null) {
            return new FirstAncestorInfo(null, false, false);
        }
        // 获取左右子树信息
        FirstAncestorInfo aAncestorInfo = firstAncestorProcess(head.left, a, b);
        FirstAncestorInfo bAncestorInfo = firstAncestorProcess(head.right, a, b);

        boolean isFinda = head == a || aAncestorInfo.isFinda || bAncestorInfo.isFinda;
        boolean isFindb = head == b || aAncestorInfo.isFindb || bAncestorInfo.isFindb;

        Node ancestor = null;
        if (aAncestorInfo.ancestor != null) {
            ancestor = aAncestorInfo.ancestor;
        }
        if (bAncestorInfo.ancestor != null) {
            ancestor = bAncestorInfo.ancestor;
        }
        if (ancestor == null && isFinda && isFindb) {
            ancestor = head;
        }
        return new FirstAncestorInfo(ancestor, isFinda, isFindb);
    }

    /**
     * 最低公共祖先子树返回信息
     */
    @Data
    @AllArgsConstructor
    public static class FirstAncestorInfo {
        /**
         * 最低公共祖先节点
         */
        private Node ancestor;
        /**
         * 是否找到a节点
         */
        private Boolean isFinda;
        /**
         * 是否找到b节点
         */
        private Boolean isFindb;
    }
}
