package cn.com.zhanss.annotation.proxy;

import cn.com.zhanss.annotation.service.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @desc 计算器代理
 * @author zhanshuchan
 * @date 2021/2/23
 */
public class CalculatorProxy {

    public static Calculator getProxy(Calculator calculator) {

        ClassLoader classLoader = calculator.getClass().getClassLoader();
        Class<?>[] interfaces = calculator.getClass().getInterfaces();
        InvocationHandler handler = (proxy, method, args) -> {
            Object result = null;
            try {
                result = method.invoke(calculator, args);
            } catch(Exception e) {

            } finally {

            }
            System.out.println("result before--->"+ result);
            return result;
        };

        Object result = Proxy.newProxyInstance(classLoader, interfaces, handler);
        System.out.println("result after--->"+ result);
        return (Calculator) result;
    }

}
