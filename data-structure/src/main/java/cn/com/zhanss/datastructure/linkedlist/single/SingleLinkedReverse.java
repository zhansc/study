package cn.com.zhanss.datastructure.linkedlist.single;

import lombok.Data;
import org.junit.Test;

/**
 * @desc 单链表反转
 * @author zhanshuchan
 * @date 2022/3/29
 */
public class SingleLinkedReverse {

    @Test
    public void test() {
        MySingleLinked<Integer> singleLinked = new MySingleLinked<>();
        singleLinked.tailPush(3);
        singleLinked.tailPush(1);
        singleLinked.tailPush(5);
        singleLinked.tailPush(0);
        singleLinked.tailPush(4);

        reverseV1(singleLinked.head);

        System.out.println("队列大小--->"+ singleLinked.size());
        System.out.println("队列头--->"+ singleLinked.headPeek());
        System.out.println("队列尾--->"+ singleLinked.tailPeek());

        MySingleLinked.Node<Integer> head = reverse(singleLinked.head);
        System.out.println("队列大小--->"+ singleLinked.size());
        System.out.println("队列头--->"+ singleLinked.headPeek());

        System.out.println("队列--->"+ head);

        Node<Integer> randomTree = randomTree(20, 5);
        Node reverseV2 = reverseV2(randomTree);
        System.out.println("reverseV2--->"+ reverseV2);
    }

    /**
     * 单链表的反转
     * @param head
     * @return
     */
    public MySingleLinked.Node<Integer> reverse(MySingleLinked.Node<Integer> head) {
        if (head == null) {
            System.out.println("参数不能为空！！");
            return null;
        }
        if (head.next == null) {
            System.out.println("单节点无需反转");
            return head;
        }
        MySingleLinked.Node<Integer> cur = head.next;
        MySingleLinked.Node<Integer> next = cur.next;
        head.next = null;
        while (cur != null) {
            cur.next = head;
            head = cur;
            cur = next;
            if (next != null) {
                next = next.next;
            }
        }
        return head;
    }

    public MySingleLinked.Node reverseV1(MySingleLinked.Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        MySingleLinked.Node pre = null;
        MySingleLinked.Node cur = head;
        MySingleLinked.Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            head = cur;
            cur = next;
        }
        return head;
    }

    /**
     * 构建一个随机长度节点随机大小的单链表
     * @param maxValue
     * @param maxLength
     * @return
     */
    public Node randomTree(int maxValue, int maxLength) {
        if (maxLength < 1) {
            return null;
        }
        int treeSize = (int) (Math.random() * maxLength);
        Node<Integer> head = new Node<>((int) (Math.random() * maxValue));
        buildNode(head, maxValue, treeSize);
        return head;
    }

    private void buildNode(Node head, int maxValue, int treeSize) {
        if (treeSize > 0) {
            Node<Integer> next = new Node<>((int) (Math.random() * maxValue));
            head.next = next;
            next.pre = head;
            buildNode(next, maxValue, -- treeSize);
        }
    }

    @Data
    private class Node<T> {
        private T value;

        private Node pre;

        private Node next;

        public Node(T value) {
            this.value = value;
        }
    }

    public Node reverseV2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = null;
        Node cur = head;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            cur.pre = next;
            pre = head;
            cur = next;
            if (cur != null) {
                head = cur;
            }
        }
        return head;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = head;
            cur = next;
            if (cur != null) {
                head = cur;
            }
        }
        return head;
    }
}
