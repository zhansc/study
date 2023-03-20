package cn.com.zhanss.datastructure.doexercise.random;

import cn.com.zhanss.datastructure.common.entity.Node;
import org.junit.Test;

/**
 * @author zhanshuchan
 * @desc 随机发生器生成
 * @date 2022/2/17
 */
public class Rand2Rand {

    /**
     * [1, 5]随机发生器
     *
     * @return
     */
    public int f() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 0 或 1等概率随机发生器
     *
     * @return
     */
    public int f01() {
        int ans;
        do {
            ans = f();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    /**
     * 000 ~ 111即[0, 7]等概率发生器
     *
     * @return
     */
    public int f07() {
        return (f01() << 2)/*第一位0或1等概率出现*/
                + (f01() << 1)/*第二位0或1等概率出现*/
                + f01()/*第三位0或1等概率出现*/;
    }

    /**
     * [0, 6]概率随机发生器
     *
     * @return
     */
    public int f06() {
        int ans;
        do {
            ans = f07();
        } while (ans == 7);
        return ans;
    }

    /**
     * [1, 7]概率随机发生器
     *
     * @return
     */
    public int g17() {
        return f06() + 1;
    }

    public int f34() {
        return Math.random() < 0.34 ? 0 : 1;
    }

    /**
     * f()函数返回的结果是0 或 1，出现0 的概率是P，出现1 的概率是 1-P，
     * 问怎么仅用f()得到g()函数返回的结果是0 或 1，且出现0 的概率和出现1的概率相等？
     * @return
     */
    public int g01() {
        int ans;
        do {
            ans = f();
        } while (ans == f());
        return ans;
    }

    /**
     * 指定最大数组长度和数据组最大值，返回一个随机数组
     *
     * @param maxLen
     * @param maxValue
     * @return
     */
    public static int[] lenRandomValueRandom(int maxLen, int maxValue) {
        int arrLen = (int) (Math.random() * maxLen);
        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    @Test
    public void testSingleList() {
        Node head = new Node();
        head.setValue(1);
        Node node1 = new Node();
        node1.setValue(2);
        head.setNext(node1);
        Node node2 = new Node();
        node2.setValue(3);
        node1.setNext(node2);

//        recursion(head, 4);
        printNode(head);

        head = reversSingleList(head);
        System.out.println("\n==================");

        printNode(head);
    }

    private void printNode(Node head) {
        do {
            System.out.print(head.getValue() + " ");
            head = head.getNext();
        } while (head != null);
    }

    public Node recursion(Node node, Integer nodeDepth) {
        node.setValue(--nodeDepth);
        if (nodeDepth < 1) {
            return node;
        }
        Node next = new Node();
        node.setNext(next);
        next.setNext(recursion(next, nodeDepth));
        return node;
    }

    public Node reversSingleList(Node head) {
        Node pre = null;
        Node next;

        while (head != null) {
            next = head.getNext();
            head.setNext(pre);
            pre = head;
            head = next;
        }
        return pre;
    }

}
