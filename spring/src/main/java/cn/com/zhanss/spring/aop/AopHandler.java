package cn.com.zhanss.spring.aop;

import org.springframework.aop.Advisor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 自定义AOP处理器
 *
 * @author zhanss
 * @since 2022-04-11
 */
public class AopHandler implements InvocationHandler {
    /**
     * 代理对象
     */
    private Object target;
    /**
     * 前置通知
     */
    private Advisor beforeAdvisor;
    /**
     * 后置通知
     */
    private Advisor afterAdvisor;

    /**
     * 创建代理对象
     * @param target
     * @return
     */
    public Object setObject(Object target) {
        this.target = target;
        // 指定类加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 指定代理类实现的接口
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 代理对象调用方法需要关联的handler即为当前AopHandler
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 前置通知
        if (beforeAdvisor != null) {
            beforeAdvisor.getAdvice();
        }
        // 调用代理对象方法
        Object result = method.invoke(proxy, args);
        // 后置通知
        if (afterAdvisor != null) {
            afterAdvisor.getAdvice();
        }
        return result;
    }

    public void setBeforeAdvisor(Advisor advisor) {
        this.beforeAdvisor = advisor;
    }

    public void setAfterAdvisor(Advisor advisor) {
        this.afterAdvisor = advisor;
    }

}
