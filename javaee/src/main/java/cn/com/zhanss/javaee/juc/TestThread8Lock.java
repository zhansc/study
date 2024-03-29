package cn.com.zhanss.javaee.juc;

/**
 * 测试线程8锁
 *
 * @author zhanss
 * @since 2019/9/30
 */
public class TestThread8Lock {

    public static void main(String[] args) {
        TestThread8Lock thread8Lock = new TestThread8Lock();
        new Thread(() -> thread8Lock.getTwo()).start();

        new Thread(() -> thread8Lock.getOne()).start();

        new Thread(() -> thread8Lock.getThree()).start();
    }

    private static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    private synchronized void getTwo() {

        System.out.println("two");
    }

    private void getThree() {

        System.out.println("three");
    }
}
