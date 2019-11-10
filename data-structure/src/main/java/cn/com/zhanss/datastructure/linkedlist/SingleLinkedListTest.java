package cn.com.zhanss.datastructure.linkedlist;

import org.junit.Test;

/**
 * 单链表
 *
 * @author zhanss
 * @since 2019/10/31
 */
public class SingleLinkedListTest {

    @Test
    public void testSingleLinkedList() {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addSort(new HeroNode(1, "宋江", "及时雨"));
        linkedList.addSort(new HeroNode(2, "吴用", "智多星"));
        linkedList.addSort(new HeroNode(4, "林冲", "豹子头"));
        linkedList.addSort(new HeroNode(3, "卢俊义", "玉麒麟"));

        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(4));

        System.out.println("遍历链表");
        linkedList.list();

        System.out.println("修改链表");
        HeroNode node = new HeroNode(4, "宋黑娃", "计将来");
        linkedList.edit(node);

        linkedList.list();

        System.out.println("删除链表");
        linkedList.delete(1);
        linkedList.list();

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

    public void list () {
        // 判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head;
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
}
