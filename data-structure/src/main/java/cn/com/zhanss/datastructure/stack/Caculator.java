package cn.com.zhanss.datastructure.stack;

import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * 通过栈实现计算器
 *
 * @author zhanss
 * @since 2019/12/1
 */
public class Caculator {

    @Test
    public void run() {
        String expression = "3+2*3-5";
        System.out.println("expression 结果===>:"+ caculate(expression));
    }

    /**
     * 计算
     */
    public int caculate(String expression) {
        if (StringUtils.isEmpty(expression.trim())) {
            System.out.println("请输入正确的表达式^_^!!!");
        }
        ArrayStack numStack = new ArrayStack(16);
        ArrayStack operStack = new ArrayStack(16);

        int index = 0;
        char ch = ' ';
        // 3 + 2 * 3 - 5 = 4
        while (index != expression.length()) {
            ch = expression.substring(index, index + 1).charAt(0);
            index++;
            if (operStack.isOper(ch)) {
                // 当前运算符优先级小于等于符号栈顶运算符，则出栈顶运算符
                if (!operStack.isEmpty() && operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                    int popCh = operStack.pop();
                    int num2 = numStack.pop();
                    int num1 = numStack.pop();
                    // 将计算结果再入数据栈
                    numStack.push(numStack.calculate(num1, num2, popCh));
                }
                operStack.push(ch);
            } else {
                numStack.push(ch - 48);
            }
        }
        while (!operStack.isEmpty()) {
            int popCh = operStack.pop();
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            // 将计算结果再入数据栈
            numStack.push(numStack.calculate(num1, num2, popCh));
        }
        return numStack.pop();
    }
}
