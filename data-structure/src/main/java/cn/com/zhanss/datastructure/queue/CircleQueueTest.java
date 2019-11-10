package cn.com.zhanss.datastructure.queue;

import org.junit.Test;

import java.util.Scanner;

/**
 * 环形数组实现队列
 *
 * @author zhanss
 * @since 2019/10/30
 */
public class CircleQueueTest {

    @Test
    public void testCircleQueue () {
        CircleQueue queue = new CircleQueue(4);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        System.out.println("a(add)：入队列");
        System.out.println("r(remove)：出队列");
        System.out.println("g(get)：查看队列头部");
        System.out.println("s(show)：显示队列");
        System.out.println("e(exit)：退出");
        while (loop) {
            switch (scanner.next().charAt(0)) {
                case 'a' :
                    System.out.println("请输入一个数：");
                    try {
                        queue.addQueue(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'r' :
                    try {
                        System.out.println("出队："+ queue.removeQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g' :
                    try {
                        System.out.println("对头："+ queue.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's' :
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e' :
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("Say Hello");
                    break;
            }
        }
    }
}

class CircleQueue {
    /**
     * 指向队列尾
     */
    private int rear = 0;
    /**
     * 指向队列头
     */
    private int front = 0;
    /**
     * 队列大小
     */
    private int maxSize;
    /**
     * 数组
     */
    private int[] arr;

    public CircleQueue (int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    private boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    private boolean isEmpty() {
        return rear == front;
    }

    public int addQueue (int key) {
        if (isFull()) {
            throw new RuntimeException("队列已满！");
        }
        arr[rear] = key;
        rear = (rear + 1) % maxSize;
        return key;
    }

    public void showQueue () {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        System.out.println("队列：");
        for (int i = front; i < front + used(); i ++) {
            System.out.printf("%d\t", arr[i]);
        }
    }

    public int removeQueue () {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return arr[front ++];
    }

    public int getQueue () {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return arr[front];
    }

    private int used() {
        return (rear + maxSize - front) % maxSize;
    }
}