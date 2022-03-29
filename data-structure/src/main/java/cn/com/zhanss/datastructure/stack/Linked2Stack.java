package cn.com.zhanss.datastructure.stack;

import cn.com.zhanss.datastructure.common.MyDoubleLinked;
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

        System.out.println("\n===================\n");

        MyDoubleLinked stack = new MyDoubleLinked();
        stack.headPush(5);
        stack.headPush(1);
        stack.headPush(7);
        stack.headPush(3);

        System.out.println("查看栈大小1--->"+ stack.size());
        System.out.println("查看栈头节点--->"+ stack.headPop());

        stack.delete(1);
        System.out.println("查看栈头节点--->"+ stack.headPop());
        System.out.println("查看栈头节点--->"+ stack.headPop());
        System.out.println("查看栈头节点--->"+ stack.headPop());
    }

    @Test
    public void testMyStack() {
        MyStack<String> myStack = new MyStack<>();
        myStack.push("1");
        myStack.push("3");
        myStack.push("1");
        myStack.push("5");
        myStack.push("0");
        myStack.push("4");

        System.out.println("查看栈大小1--->"+ myStack.size());
        System.out.println("查看栈最小值--->"+ myStack.min());
        System.out.println("查看栈最大值--->"+ myStack.max());
        System.out.println();

        myStack.pop();
        myStack.pop();
        myStack.pop();
        System.out.println("查看栈大小1--->"+ myStack.size());
        System.out.println("查看栈最小值--->"+ myStack.min());
        System.out.println("查看栈最大值--->"+ myStack.max());


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
     * 对头出栈
     * @return
     */
    public T headPop() {
        if (head.getNext() == null) {
            return null;
        }
        Node<T> ans = head.getNext();
        head.setNext(head.getNext().getNext());
        setSize(getSize() - 1);
        return ans.getValue();
    }

    /**
     * 对头出栈
     * @return
     */
    public T tailPop() {
        if (head.getNext() == null) {
            return null;
        }
        Node<T> ans = head.getNext();
        while (ans.getNext().getNext() != null) {
            ans.setNext(ans.getNext().getNext());
        }
        ans.getNext().setNext(null);
        setSize(getSize() - 1);
        return ans.getValue();
    }

    /**
     * 查看栈顶元素
     * @return
     */
    public T peek() {
        if (head.getNext() == null) {
            return null;
        }
        return head.getNext().getValue();
    }

    /**
     * 自定义栈
     * 实现最小、大值
     */
    class MyStack<T> {
        Integer size = 0;

        /**
         * 数据栈
         */
        Linked2Stack<T> stackData;

        /**
         * 最小值
         */
        Linked2Stack<T> stackMin;

        /**
         * 最大值
         */
        Linked2Stack<T> stackMax;

        public void push(T value) {
            if (value == null) {
                return;
            }
            if (size == null || size == 0) {
                // 空栈
                stackData = new Linked2Stack<>();
                stackMin = new Linked2Stack<>();
                stackMax = new Linked2Stack<>();
            }
            if (value instanceof Number) {
                T min = stackMin.peek();
                if (min == null || (Integer)value <= (Integer)min) {
                    stackMin.push(value);
                } else {
                    stackMin.push(min);
                }
                T max = stackMax.peek();
                if (max == null || (Integer)value >= (Integer)max) {
                    stackMax.push(value);
                } else {
                    stackMax.push(max);
                }
            } else {
                System.out.println("暂不支持非数字类型！！");
            }
            stackData.push(value);
            size ++;
        }

        /**
         * 出栈
         * @return
         */
        public T pop () {
            if (size == null || size == 0) {
                System.out.println("栈空了！！");
                return null;
            }
            stackMin.headPop();
            stackMax.headPop();
            size --;
            return stackData.headPop();
        }

        public Integer size() {
            if (size == null || size == 0) {
                System.out.println("栈空了！！");
                return null;
            }
            return size;
        }

        /**
         * 最小值
         * @return
         */
        public T min() {
            if (size == null || size == 0) {
                System.out.println("栈空了！！");
                return null;
            }
            return stackMin.peek();
        }

        /**
         * 最大值
         * @return
         */
        public T max() {
            if (size == null || size == 0) {
                System.out.println("栈空了！！");
                return null;
            }
            return stackMax.peek();
        }
    }

}
