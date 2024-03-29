package cn.com.zhanss.datastructure.queue;

import cn.com.zhanss.datastructure.linkedlist.doubly.MyDoubleLinked;
import cn.com.zhanss.datastructure.linkedlist.single.MySingleLinked;
import lombok.Data;
import org.junit.Test;

/**
 * @desc 链表实现队列
 * @author zhanshuchan
 * @date 2022/3/2
 */
@Data
public class Linked2Queue {

    @Test
    public void testBase() {
        int a = 10;
        Integer b = 10;
        System.out.println(a == b);
        Integer c = new Integer(10);
        System.out.println(c==a);
        Integer c1 = new Integer(10);
        System.out.println(c==c1);
        System.out.println(a == c);
        Integer d = Integer.valueOf(10);
        System.out.println(a == d);
        Integer e = Integer.valueOf(1300);
        Integer e1 = Integer.valueOf(1300);
        System.out.println(e == e1);
        int f = 1300;
        System.out.println(e == f);
        Integer g = new Integer(1300);
        System.out.println(g == f);
    }

    /**
     * 队列大小
     */
    private Integer size = 0;

    /**
     * 队列头结点
     */
    private MySingleLinked.Node<Integer> head = new MySingleLinked.Node<>();

    /**
     * 队列尾结点
     */
    private MySingleLinked.Node<Integer> tail = new MySingleLinked.Node<>();

    @Test
    public void test() {
        MySingleLinked linked2Queue = new MySingleLinked<>();
        linked2Queue.tailPush(3);
        linked2Queue.tailPush(1);
        linked2Queue.tailPush(5);
        linked2Queue.tailPush(0);
        linked2Queue.tailPush(4);

        System.out.println("查看队列大小--->"+ linked2Queue.size());
        System.out.println("查看队列头节点--->"+ linked2Queue.headPeek());
        System.out.println("查看队列尾节点--->"+ linked2Queue.tailPeek());

        linked2Queue.tailPop();
        System.out.println("查看队列大小--->"+ linked2Queue.size());
        System.out.println("查看队列尾节点--->"+ linked2Queue.tailPeek());

        linked2Queue.tailPop();
        System.out.println("查看队列大小--->"+ linked2Queue.size());
        System.out.println("查看队列尾节点--->"+ linked2Queue.tailPeek());

        System.out.println("\n==================\n");

        MyDoubleLinked doubleLinked = new MyDoubleLinked();
        doubleLinked.headPush(3);
        doubleLinked.headPush(1);
        doubleLinked.headPush(4);
        doubleLinked.headPush(2);
        System.out.println("查看队列大小1--->"+ doubleLinked.size());

        doubleLinked.delete(1);
        System.out.println("查看队列大小2--->"+ doubleLinked.size());
//        System.out.println("查看队列头节点--->"+ doubleLinked.headPop());

        doubleLinked.delete(4);
        System.out.println("查看队列大小3--->"+ doubleLinked.size());
        System.out.println("查看队列头节点--->"+ doubleLinked.headPop());

        doubleLinked.tailPush(7);
        doubleLinked.tailPush(5);
        System.out.println("查看队列大小4--->"+ doubleLinked.size());
        System.out.println("查看队列头节点--->"+ doubleLinked.headPop());
        System.out.println("查看队列尾节点--->"+ doubleLinked.tailPop());
    }

}
