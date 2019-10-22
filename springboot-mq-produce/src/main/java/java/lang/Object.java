package java.lang;

/**
 * 测试Object:双亲委派原则
 *
 * @author zhanss
 * @since 2019/10/16
 */
public class Object {

    private static void test() {
        System.out.println("Object");
    }

    public static void main(String[] args) {
        test();
    }
}
