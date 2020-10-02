package cn.com.zhanss.thread;

import org.junit.Test;

/**
 * 台阶的n级上法
 *
 * @author zhanss
 * @since 2019/9/18
 */
public class TestStep {

    @Test
    public void testRecursion() {
        // 165580141
        System.out.println(recursion(40));
    }

    @Test
    public void testLoop() {
        System.out.println(loop(40));
    }

    private int recursion(int n) {
        if(n == 1 || n == 2){
            return n;
        }
        return recursion(n - 1) + recursion(n - 2);
    }

    private int loop(int n) {
        if(n == 1 || n == 2){
            return n;
        }
        int end = 1;
        int last = 2;
        int sum = 0;
        for(int i = 3; i <= n; i ++) {
            sum = end + last;
            end = last;
            last = sum;
        }
        return sum;
    }

}
