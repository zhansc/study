package cn.com.zhanss.datastructure.linkedlist;

import org.junit.Test;

/**
 * 双向链表
 *
 * @author zhanss
 * @since 2019/11/17
 */
public class DoubleLinkedListTest {

    @Test
    public void testDoubleLinkedList() {
        DoubleLinkedList doubleLink = new DoubleLinkedList();
        doubleLink.add(new HeroNode2(4, "宋江", "及时雨"), true);
        doubleLink.add(new HeroNode2(1, "李逵", "黑旋风"), true);
        doubleLink.add(new HeroNode2(6, "吴用", "智多星"), false);
        doubleLink.add(new HeroNode2(2, "林冲", "豹子头"), false);
        doubleLink.add(new HeroNode2(2, "林冲", "风雪"), true);
        doubleLink.add(new HeroNode2(5, "晁盖", "晁天王"), false);
        doubleLink.add(new HeroNode2(5, "晁盖", "晁死鬼"), true);

        System.out.println("遍历双向链表\n");
        doubleLink.list(null);

        System.out.println("删除双向链表\n"+ 5);
        doubleLink.deleteNode(5);
        doubleLink.list(null);

        System.out.println("修改双向链表\n");
        doubleLink.editNode(new HeroNode2(2, "时迁", "鼓上蚤"));
        doubleLink.list(null);
    }

}

class DoubleLinkedList {

    /**
     * 头结点
     */
    private HeroNode2 head = new HeroNode2();

    public void add (HeroNode2 node, Boolean sort) {
        HeroNode2 temp = head;
        if (sort == null || sort) {
            // 添加有序链表
            while (temp.next != null) {
                if (node.no < temp.next.no) {
                    node.next = temp.next;
                    temp.next = node;
                    node.pre = temp;
                    break;
                } else if (node.no == temp.next.no) {
                    // 覆盖原来节点
                    temp.next.name = node.name;
                    temp.next.nickName = node.nickName;
                    break;
                } else {
                    temp = temp.next;
                }
            }
            // no相同时不做添加
            if (temp.next == null) {
                temp.next = node;
                node.pre = temp;
            }
        } else {
            // 顺序为添加顺序
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
            node.pre = temp;
        }
    }

    public void deleteNode (Integer no) {
        if (no == null) {
            return;
        }
        HeroNode2 temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                if (temp.next.next != null) {
                    // 删除最后一个节点
                    temp.next.next.pre = temp;
                }
                break;
            }
            temp = temp.next;
        }
    }

    public void editNode (HeroNode2 node) {
        if (node == null) {
            return;
        }
        HeroNode2 temp = head;
        while (temp.next != null) {
            if (temp.next.no == node.no) {
                temp.next.name = node.name;
                temp.next.nickName = node.nickName;
                break;
            }
            temp = temp.next;
        }
    }

    public void list (HeroNode2 node) {
        if (node == null || node.next == null) {
            node = head;
        }
        HeroNode2 temp = node;
        if (temp.next == null) {
            return;
        }
        while (temp.next != null) {
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

}