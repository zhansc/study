package cn.com.zhanss.javaee.juc;

import lombok.Data;

/**
 * 测试Volatile
 *
 * @author zhanss
 * @since 2019/9/29
 */
public class TestVolatile {

    public static void main(String[] args) {
        MyThread mt = new MyThread();
        new Thread(mt).start();

        while (true) {
            if(mt.isFlag()) {
                System.out.println("===========");
                break;
            }
        }

    }


}

@Data
class MyThread implements Runnable {

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            flag = true;
            System.out.println("-------------+"+ flag);
        }
    }
}