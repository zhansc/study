package cn.com.zhanss.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName: RPN
 * @Description:
 * @Author: jianghuiyun
 * @Date: 2021/10/8 下午1:22
 * @Version: 1.0
 */
public abstract class RPN {

    /**
     * 是否操作数
     *
     * @param elem
     * @return
     */
    protected abstract boolean isOperand(String elem);

    /**
     * 操作符判断
     *
     * @param elem
     * @return
     */
    protected abstract boolean isOperator(String elem);

    /**
     * 优先级比较
     *
     * @param op1
     * @param op2
     * @return
     */
    protected abstract int computeOperator(String op1, String op2);

    /**
     * 括号判断
     *
     * @param elem
     * @return -1：左括号；0：非括号；1：右括号
     */
    protected int checkBracket(String elem) {
        if (elem == null) {
            return 0;
        }

        String s = elem.trim();
        if (s.length() != 1) {
            return 0;
        }
        if (s.charAt(0) == '(') {
            return -1;
        } else if (s.charAt(0) == ')') {
            return 1;
        }

        return 0;


    }

    /**
     * 原表达式分隔词元
     *
     * @param expression
     * @return
     */
    protected abstract List<String> tokenize(String expression);

    /**
     * 转换为rpn表达式
     *
     * @param expressionTokens
     * @return
     */
    protected List<String> genRpn(List<String> expressionTokens) {
        Stack<String> ops = new Stack<>();
        List<String> out = new ArrayList<>();

        for (String s : expressionTokens) {
            if (isOperand(s)) {
                out.add(s);
            } else if (isOperator(s)) {
                while (!ops.isEmpty() && isOperator(ops.peek()) && computeOperator(s, ops.peek()) <= 0) {
                    out.add(ops.pop());
                }
                ops.push(s);
            } else if (checkBracket(s) == -1) {
                ops.push(s);
            } else if (checkBracket(s) == 1) {
                while (!ops.isEmpty() && checkBracket(ops.peek()) != -1) {
                    out.add(ops.pop());
                }
                if (!ops.isEmpty()) {
                    ops.pop();
                }
            }
        }

        while (!ops.isEmpty()) {
            out.add(ops.pop());
        }

        return out;
    }

    /**
     * rpn词元计算
     *
     * @param rpnExpressionTokens
     * @return
     */
    protected Object calcRpn(List<String> rpnExpressionTokens) {
        Stack<Object> result = new Stack<>();
        for (String s : rpnExpressionTokens) {
            if (isOperand(s)) {
                result.push(s);
            } else if (isOperator(s)) {
                Object v1 = result.pop();
                Object v2 = result.pop();
                Object tmp = calc(v2, v1, s);
                result.push(tmp);
            }

        }

        return result.pop();
    }

    /**
     * 操作数计算
     *
     * @param v1
     * @param v2
     * @param operator
     * @return
     */
    protected abstract Object calc(Object v1, Object v2, String operator);

    public Object calculate(String expression) {
        List<String> tokens = tokenize(expression);
        List<String> strings = genRpn(tokens);
        Object r = calcRpn(strings);

        return r;
    }

}
