package cn.com.zhanss.datastructure.queue;

import org.junit.Test;

import java.util.Stack;

/**
 * @desc 栈实现队列
 * @author zhanshuchan
 * @date 2022/3/29
 */
public class Stack2Queue {

    @Test
    public void test() {
        StackQueue queue = new StackQueue();
        queue.push(2);
        queue.push(3);
        queue.push(1);
        queue.push(5);
        queue.push(0);
        queue.push(4);

        System.out.println("队列大小--->"+ queue.size());
        System.out.println("出队--->"+ queue.pop());
        queue.push(41);
        System.out.println("出队--->"+ queue.pop());
        System.out.println("队列大小--->"+ queue.size());
    }

    public static class StackQueue<T> {
        /**
         * 队列大小
         */
        private Integer size = 0;

        /**
         * 进
         */
        private Stack<T> push = new Stack<>();

        /**
         * 出
         */
        private Stack<T> pop = new Stack<>();

        /**
         * 入队
         * @param value
         */
        public void push(T value) {
            if (value == null) {
                System.out.println("参数不能为空！！");
                return;
            }
            // push入队时，若pop不为空说明有压入之前有数据弹出，否则直接压入
            while (!pop.empty()) {
                push.push(pop.pop());
            }
            push.push(value);
            size ++;
        }

        /**
         * 出队
         * @return
         */
        public T pop() {
            if (size == null || size == 0) {
                System.out.println("空了！！");
                return null;
            }
            // pop出队时若，push不为空说明弹出之前有压入数据，否则直接弹出
            while (!push.empty()) {
                pop.push(push.pop());
            }
            size --;
            return pop.pop();
        }

        /**
         * 队列大小
         * @return
         */
        public Integer size() {
            return size;
        }
    }


}
