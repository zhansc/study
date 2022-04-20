package cn.com.zhanss.datastructure.linkedlist.single;

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
}
