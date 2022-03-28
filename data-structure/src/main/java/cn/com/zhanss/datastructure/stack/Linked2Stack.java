package cn.com.zhanss.datastructure.stack;

import cn.com.zhanss.datastructure.common.MySingleLinked;
import cn.com.zhanss.datastructure.common.entity.Node;
import lombok.Data;
import org.junit.Test;

/**
 * @desc 单链表实现栈
 * @author zhanshuchan
 * @date 2022/3/3
 */
@Data
public class Linked2Stack<T> {

    @Test
    public void testLinked2Stack() {
        MySingleLinked linked2Stack = new MySingleLinked();
        linked2Stack.push(new Node<>(1));
        linked2Stack.push(new Node<>(4));
        linked2Stack.push(new Node<>(3));
        linked2Stack.push(new Node<>(2));
        System.out.println("查看栈大小--->"+ linked2Stack.size());
        System.out.println("查看栈头节点--->"+ linked2Stack.headPeek());

        // 出栈
        linked2Stack.headPop();
        System.out.println("查看栈大小--->"+ linked2Stack.size());
        System.out.println("查看栈尾节点--->"+ linked2Stack.headPeek());

        // 出栈
        linked2Stack.headPop();
        System.out.println("查看栈大小--->"+ linked2Stack.size());
        System.out.println("查看栈尾节点--->"+ linked2Stack.headPeek());
    }

    /**
     * 栈大小
     */
    private Integer size = 0;

    /**
     * 栈头结点
     */
    private Node<T> head = new Node<>();

    public Integer size() {
        return this.size;
    }

    /**
     * 入栈
     * @param value
     * @return
     */
    public boolean push(T value) {
        if (value == null) {
            return false;
        }
        Node<T> node = new Node<>();
        node.setValue(value);
        setSize(getSize() + 1);
        if (head.getNext() == null) {
            head.setNext(node);
        } else {
            node.setNext(head.getNext());
            head.setNext(node);
        }
        return true;
    }

    /**
     * 出栈
     * @return
     */
    public T pop() {
        if (head.getNext() == null) {
            return null;
        }
        Node<T> ans = head.getNext();
        head.setNext(head.getNext().getNext());
        setSize(getSize() - 1);
        return ans.getValue();
    }

}
