package cn.com.zhanss.datastructure.tree;

import org.junit.Test;

import java.util.Stack;

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
        node3.right = node7;

        pos(node1);
        System.out.println("\n====================");
        posterior(node1);
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

}
