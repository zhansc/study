package cn.com.zhanss.datastructure.stack;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 *
 * @author zhanss
 * @since 2020/2/1
 */
public class PolanNotation {

    public static void main(String[] agrs) {
        String expression = "-0.123+((2+3)*4)-5";
        List<String> infixList = toInfixExpression(expression);
        System.out.printf("中缀表达式expression===>"+ infixList);
        System.out.println();

        List<String> suffixList = toSuffixExpression(infixList);
        System.out.printf("后缀表达式expression===>"+ suffixList);

        System.out.println("expression's result===>"+ caculate(suffixList));
    }

    /**
     * 中缀表达式
     * @param expression 表达式
     * @return
     */
    public static List<String> toInfixExpression(String expression) {
        if (StringUtils.isEmpty(expression)) {
            return null;
        }
        char ch;
        int index = 0;
        StringBuilder temp = new StringBuilder();
        List<String> ls = new ArrayList<>();
        do {
            ch = expression.charAt(index);
            // 非数字直接加入list 或 首个字符是 “-”表示负数
            if (!(index == 0 && 45 == ch) && (ch < 48 || ch > 57) && 46 != ch) {
                ls.add("" + ch);
                index ++;
            } else {
                // 清空多位数字符串
                temp.setLength(0);
                // 数字：包含多位数，小数，负数
                while (index < expression.length() && ((ch = expression.charAt(index)) >= 48 && ch <= 57) || 45 == ch || 46 == ch) {
                    temp.append(ch);
                    index ++;
                }
                ls.add(temp.toString());
            }
        } while (index != expression.length());
        return ls;
    }

    /**
     * 后缀表达式
     * @param expressions 表达式集合
     * @return
     */
    public static List<String> toSuffixExpression(List<String> expressions) {
        if (ObjectUtils.isEmpty(expressions)) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        for (String expression : expressions) {
            // 数字（包含小数，负数）
            if (expression.matches("^[-]?\\d+(.\\d+)?")) {
                list.add(expression);
            } else if (expression.contains("(")) {
                stack.push(expression);
            } else if (expression.contains(")")) {
                // 右括号：将当前一对括号中字符加入list
                while (!"(".equals(stack.peek())) {
                    list.add(stack.pop());
                }
                // 清除"("
                stack.pop();
            } else {
                // 若当前运算符优先级小于等于stack 栈顶运算符优先级，则将stack 栈顶运算符加入到list（优先级高的先运算）
                while (!stack.empty() && Operation.getPriority(expression) <= Operation.getPriority(stack.peek())) {
                    list.add(stack.pop());
                }
                // 将当前运算符加入stack
                stack.push(expression);
            }
        }
        // 将stack 中剩余字符加入list
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        // 存放在list，因此按顺序输出就是对应的后缀表达式对应的list
        return list;
    }

    /**
     * 计算
     * @param list 表达式集合
     * @return 结果
     */
    public static Double caculate (List<String> list) {
        if (ObjectUtils.isEmpty(list)) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        for (String expression : list) {
            if (expression.matches("^[-]?\\d+(.\\d+)?")) {
                // 数字则入栈
                stack.push(expression);
            } else {
                // 否则为运算符：弹出两个数字，计算再入栈
                Double num1 = Double.valueOf(stack.pop());
                Double num2 = Double.valueOf(stack.pop());
                Double result = 0.0;
                switch (expression) {
                    case "+" :
                        result = num1 + num2;
                        break;
                    case "-" :
                        result = num2 - num1;
                        break;
                    case "*" :
                        result = num1 * num2;
                        break;
                    case "/" :
                        result = num1 / num2;
                        break;
                    case "%" :
                        result = num1 % num2;
                        break;
                    default:
                        System.out.println("请输入正确的运算符！！！");
                        break;
                }
                stack.push("" + result);
            }
        }
        return Double.parseDouble(stack.pop());
    }

}

class Operation {
    private static int ADD = 1;

    private static int MIN = 1;

    private static int MUL = 2;

    private static int DIV = 2;

    public static int getPriority(String ch) {
        switch (ch) {
            case "+" :
                return 1;
            case "-" :
                return 1;
            case "*" :
                return 2;
            case "/" :
                return 2;
            default :
                System.out.printf("不存在该运算符!");
                break;
        }
        return 0;
    }
}