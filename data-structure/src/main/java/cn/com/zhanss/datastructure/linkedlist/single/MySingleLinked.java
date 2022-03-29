package cn.com.zhanss.datastructure.linkedlist.single;

import cn.com.zhanss.datastructure.common.entity.Node;

/**
 * @desc 单链表
 * @author zhanshuchan
 * @date 2022/3/28
 */
public class MySingleLinked<T> {

    /**
     * 队列大小
     */
    private Integer size = 0;

    /**
     * 队列头节点
     */
    private Node<T> head = new Node<>();

    /**
     * 队列尾节点
     */
    private Node<T> tail = new Node<>();

    /**
     * 队头入
     * @return
     */
    public void headPush(Node node) {
        if (node == null) {
            System.out.println("待插入节点不能为空");
            return;
        }
        if (isEmpty()) {
            head.setNext(node);
            tail.setNext(node);
            size ++;
            return;
        }
        node.setNext(head.getNext());
        head.setNext(node);
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
        while (temp.getNext().getNext() != null) {
            temp = temp.getNext();
        }
        tail.setNext(temp);
        // 删除队尾
        tail.getNext().setNext(null);
        size --;
        return tail.getNext().getValue();
    }

    /**
     * 查看队尾节点
     * @return
     */
    public T tailPeek() {
        if (isEmpty()) {
            return null;
        }
        return tail.getNext().getValue();
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
        head = head.getNext();
        size --;
        return temp.getNext().getValue();
    }

    /**
     * 查看队头节点
     * @return
     */
    public T headPeek() {
        if (isEmpty()) {
            return null;
        }
        return head.getNext().getValue();
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
    public void push(Node node) {
        headPush(node);
    }

    /**
     * 栈出
     * @return
     */
    public T stackPop() {
        return headPop();
    }

}
