package cn.com.zhanss.thread;

/**
 * 实例初始化
 *
 * @author zhanss
 * @since 2019/9/18
 */
public class Son extends Father {

    private int i = test();
    private static int j = method();

    public Son() {

    }

    static {
        System.out.println("子类static");
    }

    public int test() {
        System.out.println("子类test");
        return 1;
    }

    private static int method() {
        System.out.println("子类method");
        return 2;
    }

    public static final void main(String[] args) {
        Son son = new Son();
        son.test();
    }

}
