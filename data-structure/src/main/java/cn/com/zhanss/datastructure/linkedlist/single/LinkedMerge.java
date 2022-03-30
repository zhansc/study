package cn.com.zhanss.datastructure.linkedlist.single;

import org.junit.Test;

/**
 * @desc 链表合并
 * @author zhanshuchan
 * @date 2022/3/29
 */
public class LinkedMerge {
    @Test
    public void test() {
        MySingleLinked<Integer> singleLinked = new MySingleLinked<>();
        singleLinked.tailPush(2);
        singleLinked.tailPush(3);
        singleLinked.tailPush(5);
        singleLinked.tailPush(8);
        singleLinked.tailPush(12);

        MySingleLinked<Integer> singleLinked1 = new MySingleLinked<>();
        singleLinked1.tailPush(1);
        singleLinked1.tailPush(4);
        singleLinked1.tailPush(5);

        MySingleLinked.Node head = merge(singleLinked.head, singleLinked1.head);
        System.out.println("队列头--->");

    }

    public MySingleLinked.Node<Integer> merge(MySingleLinked.Node<Integer> head1,MySingleLinked. Node<Integer> head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        MySingleLinked<Integer> singleLinked = new MySingleLinked<>();
        while (head1 != null) {
            if (head2 == null) {
                break;
            }
            if (head1.value <= head2.value) {
                singleLinked.tailPush(head1.value);
                head1 = head1.next;
            } else {
                singleLinked.tailPush(head2.value);
                head2 = head2.next;
            }
        }
        // 合并head1剩余节点
        while (head1 != null) {
            singleLinked.tailPush(head1.value);
            head1 = head1.next;
        }
        // 合并head2剩余节点
        while (head2 != null) {
            singleLinked.tailPush(head2.value);
            head2 = head2.next;
        }
        return singleLinked.head;
    }
}
