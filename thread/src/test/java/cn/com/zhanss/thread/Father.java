package cn.com.zhanss.thread;

/**
 * 父类
 *
 * @author zhanss
 * @since 2019/9/18
 */
public class Father {

    private int i = test();
    private static int j = method();

    public int test() {
        System.out.println("父类test");
        return 1;
    }

    static {
        System.out.println("父类static");
    }

    private static int method() {
        System.out.println("父类method");
        return 2;
    }
}
