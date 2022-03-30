package cn.com.zhanss.datastructure.linkedlist.doubly;

import org.junit.Test;

/**
 * @desc 双链表反转
 * @author zhanshuchan
 * @date 2022/3/29
 */
public class DoubleLinkedReverse {

    @Test
    public void test() {
        MyDoubleLinked<Integer> doubleLinked = new MyDoubleLinked<>();
        doubleLinked.headPush(2);
        doubleLinked.headPush(3);
        doubleLinked.headPush(1);
        doubleLinked.headPush(5);
        doubleLinked.headPush(0);
        doubleLinked.headPush(4);
        System.out.println("队列大小--->"+ doubleLinked.size());
//        System.out.println("队列头--->"+ doubleLinked.headPop());

        MyDoubleLinked.Node<Integer> head = reverse(doubleLinked.head);
        System.out.println("队列大小--->"+ doubleLinked.size());
        System.out.println("队列--->"+ head);
        System.out.println("队列头--->"+ head.value);

    }

    /**
     * 双链表反转
     * @param head
     * @return
     */
    public MyDoubleLinked.Node<Integer> reverse(MyDoubleLinked.Node<Integer> head) {
        if (head == null) {
            System.out.println("参数不能为空！！");
            return null;
        }
        MyDoubleLinked.Node<Integer> cur = head.next;
        MyDoubleLinked.Node<Integer> next = cur.next;
        head.next = null;
        while (cur != null) {
            head.pre = cur;
            cur.next = head;
            head = cur;
            cur = next;
            if (next != null) {
                next = next.next;
            }
        }
        head.pre = null;
        return head;
    }
}
