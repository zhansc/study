package cn.com.zhanss.thread;

import java.util.concurrent.*;

/**
 * test
 *
 * @author zhanss
 * @since 2019/9/7
 */
public class Test {
    @org.junit.Test
    public void test1(){
        String str = "I am a student.";
        String[] chars = str.split(" ");
        String newStr = "";
        for(int i = chars.length; i -- > 0;){
            System.out.println(chars[i]);
            if(i != chars.length - 1){
                newStr += " ";
            }
            newStr += chars[i];
        }
        System.out.println(newStr);
    }

    @org.junit.Test
    public void test2() {
        int i = 1;
        // 先将i 的值压入操作栈，然后计算i自增，最后将栈中的值保存到i（栈中的1 将自增的2 覆盖了i = 1）
        i = i ++;
        // 先将i 的值压入操作栈，然后计算i自增，最后将栈中的值保存到j，（i = 2, j = 1）
        int j = i ++;
        int k = i + ++i * i ++;
        System.out.println("i="+ i);
        System.out.println("j="+ j);
        System.out.println("k="+ k);
    }

    /**
     * 单例模式：
     * 1）创建一个私有化的构造器
     * 2）由一个该类型的静态变量保存
     * 3）提供对外暴露的方法
     */
    @org.junit.Test
    public void test3() {
        // 直接创建实例
        Singleton1 singleton1 = Singleton1.INSTANCE;
        System.out.println(singleton1);
    }

    @org.junit.Test
    public void test4() {
        // 枚举方式
        System.out.println(Singleton2.INSTANCE);
    }

    @org.junit.Test
    public void test5() {
        // 静态代码块方式：实例化前需要加载一些其他信息
    }

    // 懒汉式
    @org.junit.Test
    public void test6() throws ExecutionException, InterruptedException {
//        singleton singleton1 = singleton.getInstance();
//        System.out.println("singleton1:"+ singleton1);

        // 创建多线程
        Callable<Singleton4> callable = () -> Singleton4.getInstance();
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton4> future1 = es.submit(callable);
        Future<Singleton4> future2 = es.submit(callable);
        Singleton4 singleton1 = future1.get();
        Singleton4 singleton2 = future2.get();

        System.out.println(singleton1 == singleton2);
        System.out.println("singleton1:"+ singleton1);
        System.out.println("singleton2:"+ singleton2);

        // 关闭线程池
        es.shutdown();
    }

    @org.junit.Test
    public void test7() {
        Singleton4 singleton4 = Singleton4.getInstance();
    }
}
