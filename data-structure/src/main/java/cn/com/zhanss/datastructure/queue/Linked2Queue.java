package cn.com.zhanss.datastructure.queue;

import cn.com.zhanss.datastructure.common.entity.Node;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @desc 单链表实现队列
 * @author zhanshuchan
 * @date 2022/3/2
 */
@Data
public class Linked2Queue {

    @Test
    public void testLinked2Queue() {
        Linked2Queue linked2Queue = new Linked2Queue();
        boolean push1 = linked2Queue.push(1);
        if (push1) {
            Node node1 = linked2Queue.peek();
            System.out.println("队列大小--->"+ linked2Queue.size);
            System.out.println("查看对头节点--->"+ node1);
        }
        boolean push2 = linked2Queue.push(2);
        if (push2) {
            System.out.println("队列大小--->"+ linked2Queue.size);
            Integer value2 = linked2Queue.pop();
            System.out.println("弹出对头节点--->"+ value2);
        }
        System.out.println("队列大小--->"+ linked2Queue.size);
        System.out.println("查看对头节点--->"+ linked2Queue.peek());

    }

    @Test
    public void testSorted() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 5; i ++) {
            User user = new User();
            user.setId(i);
            user.setName("name"+ i);
            user.setAge("2022-03-0"+ i +" 16:5"+i+":3"+i);
            users.add(user);
        }
        users.get(1).setAge("2022-03-03 16:53:34");
        users.get(2).setAge(null);
        System.out.println("users--->"+ users);
        List<User> ageIsnullUsers = new ArrayList<>();
        List<User> sortedUsers = users.stream()
                .filter(item -> {
                    if (StringUtils.isBlank(item.getAge())) {
                        ageIsnullUsers.add(item);
                        return false;
                    }
                    return true;
                })
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
        sortedUsers.addAll(ageIsnullUsers);
        System.out.println("sortedUsers--->"+ sortedUsers);
    }

    @Test
    public void testBase() {
        int a = 10;
        Integer b = 10;
        System.out.println(a == b);
        Integer c = new Integer(10);
        System.out.println(c==a);
        Integer c1 = new Integer(10);
        System.out.println(c==c1);
        System.out.println(a == c);
        Integer d = Integer.valueOf(10);
        System.out.println(a == d);
        Integer e = Integer.valueOf(1300);
        Integer e1 = Integer.valueOf(1300);
        System.out.println(e == e1);
        int f = 1300;
        System.out.println(e == f);
        Integer g = new Integer(1300);
        System.out.println(g == f);
    }

    /**
     * 队列大小
     */
    private Integer size = 0;

    /**
     * 队列头结点
     */
    private Node head = new Node();

    /**
     * 队列尾结点
     */
    private Node tail = new Node();

    public Integer size() {
        return this.size;
    }

    /**
     * 在队尾插入节点
     * @param value
     * @return
     */
    public boolean push(Integer value) {
        if (value == null) {
            return false;
        }
        Node node = new Node();
        node.setValue(value);
        setSize(getSize() + 1);
        if (head.getNext() == null && tail.getNext() == null) {
            head.setNext(node);
            tail.setNext(node);
        } else {
            tail.getNext().setNext(node);
            tail.setNext(node);
        }
        return true;
    }

    /**
     * 查看对头节点
     * @return
     */
    public Node peek() {
        if (head.getNext() == null) {
            return null;
        }
        return head.getNext();
    }

    /**
     * 弹出对头节点
     * @return
     */
    public Integer pop() {
        if (head.getNext() == null) {
            return null;
        }
        Node ans = head.getNext();
        head.setNext(head.getNext().getNext());
        if (head.getNext() == null) {
            tail.setNext(null);
        }
        setSize(getSize() - 1);
        return ans.getValue();
    }

    @Data
    class User {
        private Integer id;

        private String name;

        private String age;
    }
}
