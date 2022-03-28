package cn.com.zhanss.datastructure.linkedlist.single;

import cn.com.zhanss.datastructure.linkedlist.HeroNode;
import org.junit.Test;

/**
 * 单链表
 *
 * @author zhanss
 * @since 2019/10/31
 */
public class LinkedListTest1 {

    @Test
    public void testSingleLinkedList() throws Exception {
        LinkedList linkedList = new LinkedList();
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
        System.out.println("倒数第几个节点："+ linkedList.getIndex(0));

        linkedList.reverseNode();
        System.out.println("\n反转链表");
        linkedList.list();
    }

}
