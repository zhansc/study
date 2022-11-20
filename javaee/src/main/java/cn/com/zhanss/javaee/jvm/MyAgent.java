package cn.com.zhanss.javaee.jvm;

import java.lang.instrument.Instrumentation;

/**
 * 自定义javaagent
 *
 * @author zhanss
 * @since 2022-10-18
 */
public class MyAgent {

    /**
     * 动态代理一种方式
     * 1、将当前类打包
     * 2、修改MyAgent的Edit Configurations，增加VM Options配置
     * -javaagent:E:\youzan\IdeaProjects\study\javaee\target\javaee-0.0.1-SNAPSHOT.jar=testjavaagent
     * -javaagent:打包的绝对路径=premain方法的第一个参数
     * 3、执行main方法
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("hello javaAgent");
    }

    /**
     * @param agentArgs 是premain 函数得到的程序参数，随同 “–javaagent”一起传入
     * @param inst 一个java.lang.instrument.Instrumentation 的实例，由 JVM 自动传入
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("=========premain方法执行1========");
        System.out.println(agentArgs);
    }

    /**
     * 如果不存在 premain(String agentArgs, Instrumentation inst) 则会执行
     *  premain(String agentArgs)
     * @param agentArgs
     */
    public static void premain(String agentArgs) {
        System.out.println("=========premain方法执行2========");
        System.out.println(agentArgs);
    }
}

