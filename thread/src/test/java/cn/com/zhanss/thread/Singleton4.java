package cn.com.zhanss.thread;

import java.util.Properties;

/**
 * 懒汉式
 * 静态内部类单例模式实现线程安全
 *
 * 在内部类被加载和初始化时，才创建INSTANCE实例对象
 * 静态内部类不会随着外部类的加载和初始化而初始化，他是单独去加载和初始化的；
 * 因为是在内部类加载和初始化时创建的，因此是线程安全的
 *
 * @author zhanss
 * @since 2019/9/17
 */
public class Singleton4 {

    private Singleton4() {}

    // 静态内部类
    private static class Inner {
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    public static Singleton4 getInstance() {

        return Inner.INSTANCE;
    }
}
