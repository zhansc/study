package cn.com.zhanss.annotation.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @desc 安全工具
 * 多个切面类执行顺序，默认是按照切面类名称首字母字典排序执行；
 * 可以通过 @Order 注解指定执行顺序，值越小优先级越大
 * @author zhanshuchan
 * @date 2021/2/24
 */
@Aspect
@Component
@Order(12)
public class SecurityUtil {
    /**
     * @Pointcut 抽象切入点表达式
     */
    @Pointcut("execution(public Integer cn.com.zhanss.annotation.service.MyCalculator.*(Integer,Integer))")
    public void pointCut() {}

    /**
     * 此处execution表达式绑定了要通知的切入点
     */
    @Before("pointCut() && execution(* *(..))")
    public static void start(JoinPoint joinPoint) {
        System.out.println("security---"+ joinPoint.getSignature().getName()+ "方法执行开始了。。。"+ joinPoint.getArgs());
    }

    @After("@annotation(cn.com.zhanss.annotation.utils.Security)")
    public static void stop(JoinPoint joinPoint) {
        System.out.println("security---"+ joinPoint.getSignature().getName()+ "方法执行结束了。。。");
        try {
            String methodName = joinPoint.getSignature().getName();
            Class<?> targetClass = joinPoint.getTarget().getClass();
            Class<?>[] par = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
            Method targetMethod = targetClass.getMethod(methodName, par);
            Security security = targetMethod.getAnnotation(Security.class);
            String add = security.add();
            String added = security.added();
            System.out.println("targetMethod---"+ targetMethod+"-----"+ security);

        } catch (Exception e) {

        }
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public static void logException(Exception e) {
        System.out.println("security---"+ "方法执行异常了。。。"+ e.getMessage());
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public static void logFinally(Object result) {
        result = 100;
        System.out.println("security---"+ "执行完成。。。结果="+ result);
    }

}
