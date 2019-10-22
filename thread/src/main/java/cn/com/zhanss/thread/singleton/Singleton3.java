package cn.com.zhanss.thread.singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * 单例模式
 *
 * @author zhanss
 * @since 2019/9/28
 */

public class Singleton3 {
    public static final Singleton3 INSTANCE;
    private String info;

    static{
        try {
            Properties pro = new Properties();

            pro.load(Singleton3.class.getClassLoader().getResourceAsStream("singleton.properties"));

            INSTANCE = new Singleton3(pro.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Singleton3(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "singleton [info=" + info + "]";
    }

    public static void main(String[] args) {
        Singleton3 singleton3 = Singleton3.INSTANCE;
        System.out.println(singleton3);
    }
}
