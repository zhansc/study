package cn.com.zhanss.datastructure.stack;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @desc 队列实现栈
 * @author zhanshuchan
 * @date 2022/3/29
 */
public class Queue2Stack {

    @Test
    public void testStack() {
        QueueStack<Integer> stack = new QueueStack<>();
        stack.push(2);
        stack.push(3);
        stack.push(1);
        stack.push(5);
        stack.push(0);
        stack.push(4);

        System.out.println("栈大小--->"+ stack.size());
        System.out.println("出栈--->"+ stack.pop());
        stack.push(14);
        System.out.println("出栈--->"+ stack.pop());
        stack.pop();
        stack.pop();
        System.out.println("栈大小--->"+ stack.size());

    }

    public static class QueueStack<T> {
        private Integer size = 0;

        private Queue<T> push = new LinkedBlockingQueue<>();

        private Queue<T> pop = new LinkedBlockingQueue<>();

        public void push(T value) {
            if (value == null) {
                System.out.println("参数不能为空！！");
                return;
            }
            while (!pop.isEmpty()) {
                push.add(pop.poll());
            }
            push.add(value);
            size ++;
        }

        public T pop() {
            if (size == null || size == 0) {
                System.out.println("空了！！");
                return null;
            }
            while (push.size() > 1) {
                pop.add(push.poll());
            }
            size --;
            return push.poll();
        }

        public Integer size() {
            return size;
        }
    }

}
