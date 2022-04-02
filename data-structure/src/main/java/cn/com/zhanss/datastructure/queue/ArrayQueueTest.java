package cn.com.zhanss.datastructure.queue;

import org.junit.Test;

/**
 * 数组实现队列
 *
 * @author zhanss
 * @since 2019/10/30
 */
public class ArrayQueueTest {

    @Test
    public void testArrayQueue() {
        Array2QueueStack queue = new Array2QueueStack();
        queue.push(2);
        queue.push(3);
        queue.push(1);
        queue.push(5);
        queue.push(0);
        queue.push(4);

        System.out.println("队列大小--->"+ queue.size());
        System.out.println("出队--->"+ queue.headPop());
        queue.push(41);
        System.out.println("出队--->"+ queue.headPop());
        System.out.println("队列大小--->"+ queue.size());

    }

    @Test
    public void testArrayStack() {
        Array2QueueStack stack = new Array2QueueStack();
        stack.push(2);
        stack.push(3);
        stack.push(1);
        stack.push(5);
        stack.push(0);
        stack.push(4);

        System.out.println("栈大小--->"+ stack.size());
        System.out.println("出栈--->"+ stack.tailPop());
        stack.push(14);
        System.out.println("出栈--->"+ stack.tailPop());
        System.out.println("栈大小--->"+ stack.size());

    }
}

class Array2QueueStack {

    private Integer defaultMaxSize = 10;

    private Integer[] arr;

    private Integer back = 0;

    public Array2QueueStack() {
        arr = new Integer[defaultMaxSize];
    }

    public Array2QueueStack(Integer maxSize) {
        arr = new Integer[maxSize];
    }

    public void push(Integer value) {
        if (value == null) {
            System.out.println("参数不能为空！！！");
            return;
        }
        arr[back ++] = value;
    }

    /**
     * 头出
     * @return
     */
    public Integer headPop() {
        if (back == 0) {
            System.out.println("空了！！！");
            return null;
        }
        Integer ans = arr[0];
        // 头出，需要将后置位数据前移一位
        for(int i = 0; i < back; i ++) {
            arr[i] = arr[i + 1];
        }
        arr[-- back] = null;
        return ans;
    }

    /**
     * 尾出
     */
    public Integer tailPop() {
        if (back == 0) {
            System.out.println("空了！！！");
            return null;
        }
        return arr[-- back];
    }

    /**
     * 容器大小
     * @return
     */
    public Integer size() {
        return back;
    }
}
