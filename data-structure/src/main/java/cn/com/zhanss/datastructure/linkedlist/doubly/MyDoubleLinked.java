package cn.com.zhanss.datastructure.linkedlist.doubly;

import lombok.NoArgsConstructor;

/**
 * @desc 双向链表
 * @author zhanshuchan
 * @date 2022/3/28
 */
public class MyDoubleLinked<T> {

    @NoArgsConstructor
    public static class Node<T> {

        public T value;

        public Node<T> pre;

        public Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * 队列大小
     */
    private Integer size = 0;

    /**
     * 队列头节点
     */
    public Node<T> head;

    /**
     * 队列尾节点
     */
    private Node<T> tail;

    public boolean isEmpty() {
        return size == null || size == 0;
    }

    public Integer size() {
        return size;
    }

    /**
     * 对头插入
     * @param value
     */
    public void headPush(T value) {
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            node.next.pre = node;
            head = node;
        }
        size ++;
    }

    /**
     * 队尾插入
     * @param value
     */
    public void tailPush(T value) {
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.pre = tail;
            tail.next = node;
            tail = node;
        }
        size ++;
    }

    /**
     * 删除指定value
     * @param value
     */
    public boolean delete(T value) {
        if (isEmpty()) {
            System.out.println("队列为空，无法删除");
            return false;
        }
        Node<T> node = head;
        if (head.value.equals(value)) {
            // 头结点
            this.headPop();
            return true;
        } else if (tail.value.equals(value)) {
            // 尾结点
            this.tailPop();
            return true;
        } else {
            while(node.value != null && !node.value.equals(value)) {
                node= node.next;
            }
        }
        node.next.pre = node.pre;
        node.pre.next = node.next;
        size --;
        return true;
    }

    /**
     * 对头出
     * @return
     */
    public T headPop() {
        if (isEmpty()) {
            System.out.println("队列为空，无法弹出");
            return null;
        }
        Node<T> ans = head;
        // 仅剩一个节点
        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.pre = null;
        }
        size --;
        return ans.value;
    }

    /**
     * 对尾出
     * @return
     */
    public T tailPop() {
        if (isEmpty()) {
            System.out.println("队列为空，无法弹出");
            return null;
        }
        Node<T> ans = tail;
        // 仅剩一个节点
        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.pre;
            tail.next = null;
        }
        size --;
        return ans.value;
    }

    /**
     * 双链表反转
     */
    public void reverse() {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        Node<T> pre = null;
        Node<T> cur = head;
        tail = head;
        while (cur != null) {
            cur = cur.next;
            head.next = pre;
            head.pre = cur;

            pre = head;
            head = cur;
        }
        head = pre;
    }
}
