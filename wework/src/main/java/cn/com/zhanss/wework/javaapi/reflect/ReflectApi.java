package cn.com.zhanss.wework.javaapi.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @desc 反射API
 * @author zhanshuchan
 * @date 2020/11/8
 */
public class ReflectApi {

    @Test
    public void test() throws Exception {
        Class clazz = Class.forName("cn.com.zhanss.reflect.Student");
        System.out.println("clazz.getName--->"+ clazz.getName());
        // 获取当前类及父类的公共属性
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("-------------------");
        fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("--------操作私有属性-----------");
        Field address = clazz.getDeclaredField("address");
        // 设置私有属性允许被访问
        address.setAccessible(true);
        System.out.println(address.getName());
        Object o = clazz.newInstance();
        address.set(o, "杭州市");
        System.out.println(o.toString());

        System.out.println("-------------------");
        Method setName = clazz.getMethod("setName", String.class);
        System.out.println("setName---"+setName.getName());

        System.out.println("----------getMethods获取该类及所有父类的所有公共方法---------");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("----------getDeclaredMethods获取当前类所有的方法（包含私有方法）---------");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
        }

        System.out.println("----------操作私有方法---------");
        // 获取私有方法
        Method add = clazz.getDeclaredMethod("add", int.class, int.class);
        System.out.println("add--->"+ add);
        add.setAccessible(true);
        add.invoke(o, 123, 123);

        System.out.println("----------获取公共构造方法方法---------");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("----------获取所有构造方法---------");
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            System.out.println(constructor.getName());
        }

        System.out.println("----------操作私有构造方法---------");
        Constructor declaredConstructor = clazz.getDeclaredConstructor(int.class, String.class);
        System.out.println(declaredConstructor.getName());
        declaredConstructor.setAccessible(true);
        Object java = declaredConstructor.newInstance(23, "java");
        System.out.println(java);
    }

}
