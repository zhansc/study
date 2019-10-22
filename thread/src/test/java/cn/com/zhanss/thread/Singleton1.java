package cn.com.zhanss.thread;

/**
 * 单例模式：
 * 懒汉式：在类加载的时候初始化，不存在线程安全问题
 * 1）创建一个私有化的构造器
 * 2）由一个该类型的静态变量保存
 * 3）提供对外暴露的方法
 * @author zhanss
 * @since 2019/9/17
 */
public class Singleton1 {

    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {}

    public void test() {
        System.out.println("父类test");
    }

}
