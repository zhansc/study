package cn.com.zhanss.datastructure.linkedlist.single;

import cn.com.zhanss.datastructure.linkedlist.HeroNode;

/**
 * @desc LinkedList
 * @author zhanshuchan
 * @date 2022/3/28
 */
public class LinkedList {
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
        // 若需要在链表中间插入节点，此处和break上一句代码重复，但是不影响结果；若是在链表末尾插入节点这句是必须额
        temp.next = node;
    }

    public void print(HeroNode head) {
        if (head == null) {
            return;
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.print(temp.name + " ");
            temp = temp.next;
        }
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

    public void reverseNode1() {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        HeroNode pre = null;
        // head.next是一个指针
        HeroNode cur = head.next;
        while (cur != null) {
            cur = cur.next;
            head.next.next = pre;
            pre = head.next;
            head.next = cur;
        }
        head.next = pre;
    }

    public void reverseNode() {
        HeroNode current = head.next;
        // 链表为空或链表只有一个节点，直接返回
        if (current.next == null || current.next.next == null) {
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
}