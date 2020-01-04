package cn.com.zhanss.datastructure.stack;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 链表实现栈
 *
 * @author zhanss
 * @since 2019/11/27
 */
public class ArrayStackTest {

    @Test
    public void testArrayStack() {
        ArrayStack stack = new ArrayStack(4);
        System.out.println(stack.push(2));
        stack.list();
        stack.push(3);
        stack.list();
        stack.push(4);
        stack.list();
        stack.push(5);
        stack.list();
        // 出栈
        System.out.println("出栈==>"+ stack.pop());
        stack.push(6);
        // 遍历栈
        stack.list();
    }



}

class ArrayStack {
    /**
     * 栈大小
      */
    private int maxSize;
    /**
     * 数组模拟栈
     */
    private int[] stack;
    /**
     * 栈顶，-1 表示空
     */
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 栈满
     * @return
     */
    public Boolean isFull() {
        return top == maxSize -1;
    }

    /**
     * 栈空
     * @return
     */
    public Boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     * @param value 值
     * @return 入栈成功否
     */
    public Boolean push(int value) {
        if (isFull()) {
            System.out.println("栈满了");
            return false;
        }
        top ++;
        stack[top] = value;
        return true;
    }

    /**
     * 出栈
     * @return 栈顶元素
     */
    public int pop() {
        if (isEmpty()) {
            System.out.println("栈空了");
            return 0;
        }
        int value = stack[top];
        top --;
        return value;
    }

    /**
     * 查看栈顶元素
     * @return
     */
    public int peek() {
        return stack[top];
    }

    public void list() {
        if (isEmpty()) {
            return;
        }
        for (int i = top; i > -1; i --) {
            System.out.printf("stack[%d]=%d\n",i, stack[i]);
        }
        System.out.println();
    }

    /**
     * 计算的优先级
     * @param oper 操作符
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/' || oper == '%') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是否为操作符
     * @param oper 操作符
     * @return
     */
    public boolean isOper(char oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/' || oper == '%';
    }

    /**
     * 计算
     * @param num1
     * @param num2
     * @param opr 运算符
     * @return
     */
    public int calculate(Integer num1, Integer num2, int opr) {
        int result = 0;
        if (num1 == null || num2 == null) {
            System.out.println("请输入正确的格式！");
            return result;
        }
        switch (opr) {
            case '+' :
                result = num1 + num2;
                break;
            case '-' :
                result = num1 - num2;
                break;
            case '*' :
                result = num1 * num2;
                break;
            case '/' :
                result = num1 / num2;
                break;
            case '%' :
                result = num1 % num2;
                break;
            default:
                System.out.println("请输入正确的运算符！！！");
                break;
        }
        return result;
    }
}
