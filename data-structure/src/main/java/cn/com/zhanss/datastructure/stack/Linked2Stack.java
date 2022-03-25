package cn.com.zhanss.datastructure.stack;

import cn.com.zhanss.datastructure.common.entity.Node;
import lombok.Data;
import org.junit.Test;

/**
 * @desc 单链表实现栈
 * @author zhanshuchan
 * @date 2022/3/3
 */
@Data
public class Linked2Stack {

    @Test
    public void testLinked2Stack() {
        Linked2Stack linked2Stack = new Linked2Stack();
        linked2Stack.push(1);
        boolean push2 = linked2Stack.push(2);
        if (push2) {
            System.out.println("队列大小--->"+ linked2Stack.size);
            linked2Stack.push(3);
            Integer value2 = linked2Stack.pop();
            System.out.println("弹出对头节点--->"+ value2);
        }
        linked2Stack.push(4);
        Integer value3 = linked2Stack.pop();
        System.out.println("弹出对头节点--->"+ value3);
        System.out.println("队列大小--->"+ linked2Stack.size);
    }

    /**
     * 栈大小
     */
    private Integer size = 0;

    /**
     * 栈头结点
     */
    private Node head = new Node();

    public Integer size() {
        return this.size;
    }

    /**
     * 入栈
     * @param value
     * @return
     */
    public boolean push(Integer value) {
        if (value == null) {
            return false;
        }
        Node node = new Node();
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
    public Integer pop() {
        if (head.getNext() == null) {
            return null;
        }
        Node ans = head.getNext();
        head.setNext(head.getNext().getNext());
        setSize(getSize() - 1);
        return ans.getValue();
    }

}
