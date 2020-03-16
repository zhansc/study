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
        String expression = "201.09+21*5*2-2+1";
        System.out.printf("expression 结果===>:%.3f", caculate(expression));
    }

    /**
     * 计算
     */
    public static double caculate(String expression) {
        if (StringUtils.isEmpty(expression.trim())) {
            System.out.println("请输入正确的表达式^_^!!!");
        }
        ArrayStack numStack = new ArrayStack(16);
        ArrayStack operStack = new ArrayStack(16);
        StringBuilder numTemp = new StringBuilder();

        int index = 0;
        char ch = ' ';
        // 3 + 2 * 3 - 5 = 4
        while (index != expression.length()) {
            ch = expression.substring(index, index + 1).charAt(0);
            index ++;
            if (operStack.isOper(ch)) {
                // 将临时保存的数据压入numStack
                numStack.push(Double.parseDouble(numTemp.toString()));
                // 清空数字缓存
                numTemp.setLength(0);

                // 当前运算符优先级小于等于符号栈顶运算符，则出栈顶运算符
                if (!operStack.isEmpty() && operStack.priority(ch) <= operStack.priority((int)operStack.peek())) {
                    double popCh = operStack.pop();
                    double num2 = numStack.pop();
                    double num1 = numStack.pop();
                    // 将计算结果再入数据栈
                    numStack.push(numStack.calculate(num1, num2, (int)popCh));
                }
                operStack.push(ch);
            } else if (numStack.isNum(ch + "")) {
                numTemp.append(ch - 48);
            } else if ('.' == ch) {
                numTemp.append(ch);
            }
        }
        // 最后一个数字加入数字栈
        numStack.push(Integer.valueOf(numTemp.toString()));
        while (!operStack.isEmpty()) {
            double popCh = operStack.pop();
            double num2 = numStack.pop();
            double num1 = numStack.pop();
            // 将计算结果再入数据栈
            numStack.push(numStack.calculate(num1, num2, (int)popCh));
        }
        return numStack.pop();
    }
}
