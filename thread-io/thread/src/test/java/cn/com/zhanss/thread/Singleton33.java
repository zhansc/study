package cn.com.zhanss.thread;

import java.io.IOException;
import java.util.Properties;

/**
 * 懒汉式
 * 需要的时候调用getInstance()获取
 *
 * @author zhanss
 * @since 2019/9/17
 */
public class Singleton33 {

    private static final Singleton33 INSTANCE;

    private String info;

    static {
        Properties properties = new Properties();
        try {
            properties.load(Singleton33.class.getClassLoader().getResourceAsStream("singleton.properties"));
            INSTANCE = new Singleton33(properties.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private Singleton33(String info) {
        this.info = info;
    }


    public static void main(String[] args) {
        Singleton33 singleton = Singleton33.INSTANCE;
        System.out.println("singleton:"+ singleton);
    }
}
