package cn.com.zhanss.datastructure.queue;

import cn.com.zhanss.datastructure.linkedlist.doubly.MyDoubleLinked;
import cn.com.zhanss.datastructure.linkedlist.single.MySingleLinked;
import cn.com.zhanss.datastructure.common.entity.Node;
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
    public void testLinked2Queue() {
        MySingleLinked linked2Queue = new MySingleLinked();
        linked2Queue.push(new Node<>(3));
        linked2Queue.push(new Node<>(1));
        linked2Queue.push(new Node<>(4));
        linked2Queue.push(new Node<>(2));
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
