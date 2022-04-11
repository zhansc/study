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

        // 指定由哪个ClassLoader来加载代理对象
        ClassLoader classLoader = calculator.getClass().getClassLoader();
        // 提供一组接口供代理对象实现
        Class<?>[] interfaces = calculator.getClass().getInterfaces();
        // 代理对象在调用方法的时候，会关联的handler对象
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

        // 创建代理对象
        Object result = Proxy.newProxyInstance(classLoader, interfaces, handler);
        System.out.println("result after--->"+ result);
        // 为什么这里能够强转，就是因为上面提供了一组接口，代理对象实现了接口
        return (Calculator) result;
    }

}
