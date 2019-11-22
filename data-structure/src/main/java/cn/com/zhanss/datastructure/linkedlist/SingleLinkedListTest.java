package cn.com.zhanss.datastructure.linkedlist;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 单链表
 *
 * @author zhanss
 * @since 2019/10/31
 */
public class SingleLinkedListTest {

    @Test
    public void testSingleLinkedList() throws Exception {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addSort(new HeroNode(1, "宋江", "及时雨"));
        linkedList.addSort(new HeroNode(2, "吴用", "智多星"));
        linkedList.addSort(new HeroNode(5, "林冲", "豹子头"));
        linkedList.addSort(new HeroNode(3, "卢俊义", "玉麒麟"));

        SingleLinkedList linkedList2 = new SingleLinkedList();
        linkedList2.addSort(new HeroNode(5, "武松", "大朗哥"));
        linkedList2.addSort(new HeroNode(7, "呼延灼", "大彪客"));
        linkedList2.addSort(new HeroNode(8, "李逵", "黑旋风"));
        HeroNode newHead = linkedList.mergeLink(linkedList.head, linkedList2.head);
        System.out.println("\n合并两个链表\n");
        linkedList.list(newHead);

        System.out.println("\n根据no获取链表节点");
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(4));

        System.out.println("遍历链表");
        linkedList.list(linkedList.head);

        System.out.println("修改链表");
        HeroNode node = new HeroNode(4, "宋黑娃", "计将来");
        linkedList.edit(node);

        linkedList.list(null);

        System.out.println("删除链表");
        linkedList.delete(1);
        linkedList.list(null);
        System.out.println("倒数第几个节点："+ linkedList.getIndex(0));

        linkedList.reverseNode();
        System.out.println("\n反转链表");
        linkedList.list(null);

        System.out.println("\n逆向输出链表");
        linkedList.reversePrint();

    }

}

class SingleLinkedList {
    /**
     * 头结点
     */
    HeroNode head = new HeroNode();

    /**
     * 新增
     * @param node
     */
    public void add (HeroNode node) {
        HeroNode temp = head;
        while (temp.next != null) {
            // 遍历到链表末尾
            temp = temp.next;
        }
        temp.next = node;
    }

    public void addSort (HeroNode node) {
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no > node.no) {
                node.next = temp.next;
                temp.next = node;
                break;
            }
            // 遍历到链表末尾
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 根据排名获取英雄信息
     * @param no
     * @return
     */
    public HeroNode get (int no) {
        HeroNode temp = head;
        while (temp != null) {
            if (temp.no == no) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void list (HeroNode node) {
        if (node == null || node.next == null) {
            node = head;
        }
        // 判断链表是否为空
        if(node.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = node;
        while (temp.next != null) {
            // 遍历到链表末尾
            temp = temp.next;
            System.out.println(temp);
        }
    }

    public void edit (HeroNode node) {
        HeroNode temp = head;
        while (temp != null) {
            if (temp.no == node.no) {
                temp.setName(node.name);
                temp.setNickName(node.nickName);
                break;
            }
            // 遍历到链表末尾
            temp = temp.next;
        }
    }

    public void delete (int no) {
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                break;
            }
            // 遍历到链表末尾
            temp = temp.next;
        }
    }

    public int getLength() {
        HeroNode temp = head;
        int length = 0;
        while (temp.next != null) {
            length ++;
            temp = temp.next;
        }
        return length;
    }

    public HeroNode getLastIndex(int index) throws Exception {
        int length = getLength();
        if (index <= 0 || index > length) {
            throw new Exception("index outofIndex");
        }
        HeroNode temp = head;
        for (int i = 0; i <= length - index; i ++) {
            temp = temp.next;
        }
        return temp;
    }

    public HeroNode getIndex(int index) throws Exception {
        int length = getLength();
        if (index < 0 || index > length) {
            throw new Exception("index outofIndex");
        }
        HeroNode temp = head;
        for (int i = 0; i < index; i ++) {
            temp = temp.next;
        }
        return temp;
    }

    public void reverseNode() {
        HeroNode current = head.next;
        // 链表为空或链表只有一个节点，直接返回
        if (current == null || current.next == null) {
            return;
        }
        HeroNode next;
        HeroNode reverseHead = new HeroNode();
        while (current != null) {
            // 暂存下一个节点
            next = current.next;
            // 将向前节点的next指向新头结点的下一个节点
            current.next = reverseHead.next;
            // 新的头节点指向当前节点
            reverseHead.next = current;
            // 遍历原来链表
            current = next;
        }
        // 原来头节点重新接管新头结点
        head.next = reverseHead.next;
    }

    public void reversePrint() {
        HeroNode current = head;
        if (current.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        while (current.next != null) {
            stack.push(current.next);
            current = current.next;
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

    public HeroNode mergeLink(HeroNode node1, HeroNode node2) {
        if (node1 == null || node2 == null) {
            return null;
        }
        if (node1.next == null && node2.next != null) {
            return node2;
        }
        if (node1.next != null && node2.next == null) {
            return node1;
        }
        HeroNode newNode = new HeroNode();
        HeroNode newHead = newNode;
        HeroNode temp1 = node1;
        HeroNode temp2 = node2;
        Queue<HeroNode> queue = new LinkedList<>();
        while (temp1.next != null || temp2.next != null) {
            // temp2遍历结束，或temp1小于temp2
            if (temp1.next != null && (temp2.next == null || temp1.next.no < temp2.next.no)) {
                queue.offer(temp1.next);
                temp1 = temp1.next;
            }
            // temp1遍历结束，或temp1大于temp2
            else if (temp1.next == null || temp1.next.no > temp2.next.no) {
                queue.offer(temp2.next);
                temp2 = temp2.next;
            } else if (temp1.next.no == temp2.next.no) {
                temp1 = temp1.next;
            }
        }
        while (!queue.isEmpty()) {
            newNode.next = queue.poll();
            newNode = newNode.next;
        }
        return newHead;
    }
}
