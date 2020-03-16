package cn.com.zhanss.datastructure.linkedlist;

import lombok.AllArgsConstructor;
import org.junit.Test;

/**
 * 环形单向链表
 *
 * @author zhanss
 * @since 2019/11/20
 */
public class CircleLinkedListTest {

    @Test
    public void testCircleLinkedList() {
        doCircle(5);
        // 遍历下
        showBoy ();
        // 开始出圈，顺序：2->4->1->5->3
        countBoy(3, 2);
    }

    private Boy first = null;

    public void doCircle(int num) {
        if (num < 1) {
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= num; i ++) {
            Boy boy = new Boy(i, i+ "", null);
            if (i == 1) {
                first = boy;
                // 构成环
                first.next = first;
                curBoy = first;
            } else {
                curBoy.next = boy;
                boy.next = first;
                curBoy = boy;
            }
        }
    }

    public void showBoy () {
        if (first == null) {
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("出列的Boy标号：%d\n", curBoy.no);
            if (curBoy.next == first) {
                System.out.println("结束了！");
                return;
            }
            curBoy = curBoy.next;
        }
    }

    /**
     * 根据用户的输入，获得小孩出圈的顺序
     * @param startNo
     *        从哪开始
     * @param countNum
     *        数到几出圈
     */
    public void countBoy (int startNo, int countNum) {
        if (first == null || startNo < 1 || countNum < 1) {
            return;
        }
        Boy helper = first;
        int i = 0;
        // help始终保持在最后一个节点（头节点前面）
        while (helper.next != first) {
            // 从startNo开始报数
            if (i < startNo - 1) {
                first = first.next;
            }
            helper = helper.next;
            i ++;
        }
        while (helper != first) {
            // 同时移动countNum（数到countNum出圈）
            for (int j = 0; j < countNum - 1; j++) {
                first = first.next;
                helper = helper.next;
            }
            // 这时first指向的节点出圈
            System.out.printf("小孩%d出圈\n", first.no);
            first = first.next;
            helper.next = first;
        }
        System.out.printf("最后一个节点%d出圈", first.no);
    }
}

@AllArgsConstructor
class Boy {
    public Integer no;

    public String name;

    public Boy next;
}