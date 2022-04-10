package cn.com.zhanss.datastructure.linkedlist.single;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @desc 单链表
 * @author zhanshuchan
 * @date 2022/3/28
 */
public class MySingleLinked<T> {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
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
    public Node<T> head = new Node<>();

    /**
     * 队列尾节点
     */
    private Node<T> tail = new Node<>();

    /**
     * 队头入
     * @return
     */
    public void headPush(T value) {
        if (value == null) {
            System.out.println("待插入节点不能为空");
            return;
        }
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            head = node;
            tail = node;
            size ++;
            return;
        }
        node.next = head;
        head = node;
        size ++;
    }

    /**
     * 队头入
     * @return
     */
    public void tailPush(T value) {
        if (value == null) {
            System.out.println("待插入节点不能为空");
            return;
        }
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            head = node;
            tail = node;
            size ++;
            return;
        }
        tail.next = node;
        tail = node;
        size ++;
    }

    /**
     * 队尾出
     * @return
     */
    public T tailPop() {
        if (isEmpty()) {
            return null;
        }
        Node<T> temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        tail = temp;
        T value = tail.next.value;
        // 删除队尾
        tail.next = null;
        size --;
        return value;
    }

    /**
     * 查看队尾节点
     * @return
     */
    public T tailPeek() {
        if (isEmpty()) {
            return null;
        }
        return tail.value;
    }

    /**
     * 队头出
     * @return
     */
    public T headPop() {
        if (isEmpty()) {
            return null;
        }
        Node<T> temp = head;
        head = head.next;
        size --;
        return temp.value;
    }

    /**
     * 查看队头节点
     * @return
     */
    public T headPeek() {
        if (isEmpty()) {
            return null;
        }
        return head.value;
    }

    /**
     * 删除指定值的节点
     * @param value
     * @return
     */
    public boolean removeValue(T value) {
        if (value == null) {
            System.out.println("参数不能为空！！");
            return false;
        }
        if (isEmpty()) {
            System.out.println("队列空了！！");
            return false;
        }
        // 一个节点
        if (head.next == null) {
            if (head.value.equals(value)) {
                head = tail = null;
                size --;
                return true;
            } else {
                System.out.println("指定value不存在！！");
                return false;
            }
        }
        Node<T> node = head;
        while (node.next.value != null && !node.next.value.equals(value)) {
            node = node.next;
        }
        node.next = node.next.next;
        size --;
        return true;
    }

    /**
     * 删除第n个元素
     * @param num
     * @return
     */
    public boolean removeNum(Integer num) {
        if (num == null) {
            System.out.println("参数不能为空！！");
            return false;
        }
        if (isEmpty() || num > size) {
            System.out.println("指定的位置的元素不存在！！");
            return false;
        }
        Node<T> node = head;
        int count = 1;
        while (node.next != null) {
            count ++;
            if (num == count) {
                break;
            }
            node = node.next;
        }
        node.next = node.next.next;
        size --;
        return true;
    }

    /**
     * 链表中间节点
     * @return
     */
    public T middleValue() {
        if (isEmpty()) {
            System.out.println("空了！！");
            return null;
        }
        int middle = (size & 1) == 1 ? (size >> 1) + 1 : size >> 1;
        Node<T> node = head;
        int count = 1;
        while (node.next != null) {
            if (middle == count) {
                break;
            }
            node = node.next;
            count ++;
        }
        return node.value;
    }

    /**
     * 快速获取指定链表的中间节点
     * @param head
     * @return
     */
    public T middleNode(Node<T> head) {
        if (head == null) {
            return null;
        }
        // 一个或两个节点的时候返回头结点
        if (size == 1 || size == 2) {
            return head.value;
        }
        Node<T> fast = head;
        Node<T> slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.value;
    }
    
    /**
     * 当前列表大小
     * @return
     */
    public Integer size() {
        return size;
    }

    public boolean isEmpty() {
        return size == null || size == 0;
    }

    /**
     * 队列出
     * @return
     */
    public T queuePop() {
        return tailPop();
    }

    /**
     * 队列、栈入
     * @return
     */
    public void push(T value) {
        headPush(value);
    }

    /**
     * 栈出
     * @return
     */
    public T stackPop() {
        return headPop();
    }

}
