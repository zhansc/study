package cn.com.zhanss.juc;
/**
 * @desc 单例模式
 * @author zhanshuchan
 * @date 2020/10/21
 */
public class Singleton {


}

/**
 * 饿汉式
 */
class HungerySingleton {

    private static HungerySingleton singleton = new HungerySingleton();

    public static HungerySingleton getSingleton() {
        return singleton;
    }
}

/**
 * 懒汉式
 */
class HoonSingleton {

    private static HoonSingleton singleton;

    /**
     * double checkou lock
     * @return
     */
    public static HoonSingleton getSingleton() {
        if (singleton == null) {
            synchronized (HoonSingleton.class) {
                if (singleton == null) {
                    singleton = new HoonSingleton();
                }
            }
        }
        return singleton;
    }
}

/**
 * 静态内部类
 */
class HodlerSingleton {

    /**
     * 静态内部类跟随类一起被加载，静态内部类的成员变量只有在被调用的时候才会被实例化
     */
    private static class Hodler {
        private static HodlerSingleton singleton = new HodlerSingleton();
    }

    public static HodlerSingleton getSingleton() {
        return Hodler.singleton;
    }
}

/**
 * 枚举类
 */
class EnumSingleton {
    private enum Hodler{
        HODLER;
        private EnumSingleton singleton;
        Hodler() {
            this.singleton = new EnumSingleton();
        }
    }

    public static EnumSingleton getSingleton() {
        return Hodler.HODLER.singleton;
    }
}
