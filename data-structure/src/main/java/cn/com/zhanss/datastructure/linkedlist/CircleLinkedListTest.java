package cn.com.zhanss.datastructure.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;
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
        doCircle(3);
        // 遍历下
        showBoy ();
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
            System.out.printf("出列的Boy标号：%d", curBoy.no);
            if (curBoy.next == first) {
                System.out.println("结束了！");
                return;
            }
            curBoy = curBoy.next;
        }
    }
}

@AllArgsConstructor
class Boy {
    public Integer no;

    public String name;

    public Boy next;
}