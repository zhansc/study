package cn.com.zhanss.thread;

/**
 * 懒汉式
 * 需要的时候调用getInstance()获取
 *
 * @author zhanss
 * @since 2019/9/17
 */
public class Singleton3 {

    private static volatile Singleton3 INSTANCE;

    private Singleton3() {}

    // 存在线程安全问题
    public static Singleton3 getInstance() {
        if(INSTANCE == null) {
            // 加锁
            synchronized(Singleton3.class) {
                try {
                    // 第一个线程进来睡眠100，第二个线程进来也要等待100，第一个线程创建完成后，第二个线程
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Singleton3();
            }
        }
        return INSTANCE;
    }
}
