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
