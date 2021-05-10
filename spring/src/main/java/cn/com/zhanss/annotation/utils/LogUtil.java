package cn.com.zhanss.annotation.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @desc 日志工具
 * @author zhanshuchan
 * @date 2021/2/23
 */
@Aspect
@Component
public class LogUtil {

    @Pointcut("execution(public Integer cn.com.zhanss.annotation.service.MyCalculator.*(Integer,Integer))")
    public void pointCut() {}

    /**
     * 此处execution表达式绑定了要通知的切入点
     */
    @Before("pointCut() && execution(* *(..))")
    public static void start(JoinPoint joinPoint) {
        System.out.println("log----"+ joinPoint.getSignature().getName()+ "方法执行开始了。。。"+ joinPoint.getArgs());
     }

    @After("pointCut()")
    public static void stop(JoinPoint joinPoint) {
        System.out.println("log----"+ joinPoint.getSignature().getName()+ "方法执行结束了。。。");
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public static void logException(Exception e) {
        System.out.println("log----"+ "方法执行异常了。。。"+ e.getMessage());
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public static void logFinally(Object result) {
        result = 100;
        System.out.println("log----"+ "执行完成。。。结果="+ result);
    }

    /**
     * 执行顺序优先于普通通知
     */
    @Around("pointCut()")
    public static Object logAround(ProceedingJoinPoint pjp) {
        Signature signature = pjp.getSignature();
        Object result = null;
        try {
            System.out.println("log----"+ signature.getName()+ "环绕开始。。。"+ pjp.getArgs());
            result = pjp.proceed(pjp.getArgs());
        } catch (Throwable throwable) {
            // 需要抛出，否则普通通知无法接收
            throwable.printStackTrace();
            System.out.println("log----"+ signature.getName()+ "环绕异常。。。");
        } finally {
            System.out.println("log----"+ signature.getName()+ "环绕finaly返回。。。");
        }
        System.out.println("log----"+ signature.getName()+ "环绕结束。。。"+ result);
        return result;
    }
}
