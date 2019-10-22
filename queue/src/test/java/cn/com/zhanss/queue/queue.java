package cn.com.zhanss.queue;

import org.junit.Test;

import java.util.Stack;

/**
 * 队列
 *
 * @author zhanss
 * @since 2019/9/24
 */
public class queue {

    /**
     * 字符串反转
     */
    @Test
    public void reverse() {

        String hello = "hello World!";
        char[] chars = hello.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char c : chars) {
            stack.push(c);
        }
        StringBuilder builder = new StringBuilder();
        assert stack != null;
        while (!stack.empty()) {
            builder.append(stack.pop());
        }
        System.out.println(builder.toString());
    }

    @Test
    public void test() {
        Stack<java.io.Serializable> stack = new Stack<>();
        // 1.empty()栈是否为空
        System.out.println(stack.empty());
        // 3.进栈push()
        stack.push(new Integer(1));
        stack.push("b");
        // 2.peek()取栈顶值，但不出栈
        System.out.println(stack.peek());
        // 4.pop()出栈
        stack.pop();
        System.out.println(stack.peek());
    }

}
