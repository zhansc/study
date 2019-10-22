package cn.com.zhanss.thread;

/**
 * 线程
 *
 * @author zhanss
 * @since 2019/9/17
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        Singleton3 singleton2 = Singleton3.getInstance();
        System.out.println("singleton2:"+ singleton2);
    }
}
