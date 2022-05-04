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

    /**
     * 双向链表反转
     * @param head
     * @return
     */
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

    /**
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，
     * 那么请将最后剩余的节点保持原有顺序。
     * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        return reverseK(head, k);
    }

    /**
     * 递归单链表，每k个元素反转一次
     * @param head
     * @param k
     * @return
     */
    private ListNode reverseK(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int kIndex = k;
        ListNode nextLoop = head;
        while (kIndex > 0 && nextLoop != null) {
            nextLoop = nextLoop.next;
            kIndex --;
        }
        ListNode tail = null;
        if (nextLoop != null) {
            tail = reverseK(nextLoop, k);
        }
        // 节点数够k整数倍，则反转
        if (kIndex <= 0) {
            // 从head到nextLoop节点完成反转
            ListNode listNode = reverseInner(head, nextLoop);
            // 当前循环中的尾节点的next即为子循环返回的头节点
            head.next = tail;
            return listNode;
        }
        // 否则返回源链头
        return head;
    }

    /**
     * 将从节点head到指定节点nextLoop节点的链表反转
     * @param head
     * @param nextLoop
     * @return
     */
    private ListNode reverseInner(ListNode head, ListNode nextLoop) {
        if (head == null || head == nextLoop) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != nextLoop ){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            head = cur;
            cur = next;
        }
        return head;
    }

    @Test
    public void testReverseKGroup() {
        ListNode head = new ListNode(1);
        ListNode a = new ListNode(2);
        head.next = a;
        ListNode listNode = reverseKGroup(head, 2);
        System.out.println(listNode);
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
