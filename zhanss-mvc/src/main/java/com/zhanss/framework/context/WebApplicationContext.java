package com.zhanss.framework.context;

import com.zhanss.framework.annotation.AutoWired;
import com.zhanss.framework.annotation.Controller;
import com.zhanss.framework.annotation.Service;
import com.zhanss.framework.xml.XmlParser;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @desc SpringMVC容器
 * @author zhanshuchan
 * @date 2021/6/27
 */
public class WebApplicationContext {

    private String contextConfigLocation;

    /**
     * 缓存bean对象的全限定名
     */
    public List<String> classNameList = new ArrayList<String>();

    /**
     * 一级缓存：成熟Bean实例
     */
    public Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    public WebApplicationContext() {
    }

    public WebApplicationContext(String contextConfigLocation) {

        this.contextConfigLocation = contextConfigLocation;

    }

    public void onRefresh() {
        // 解析SpringMVC配置文件（去掉配置文件路径前缀classpath*:）
        String basePackage = XmlParser.getBasePackage(contextConfigLocation.split(":")[1]);
        // 进行循环包扫描
        for (String pack : basePackage.split(",")) {
            this.scanPackage(pack);
        }
        // 初始化bean
        this.initBean();

        // 依赖注入
        this.autoWired();
    }

    /**
     * 初始化bean
     */
    public void initBean() {

        try {
            for (String className : classNameList) {
                Class<?> clazz = Class.forName(className);
                Method[] methods = clazz.getMethods();

                // 控制层
                if (clazz.isAnnotationPresent(Controller.class)) {
                    // 获取类名
                    String clazzName = clazz.getSimpleName();
                    String beanName = clazzName.substring(0, 1).toLowerCase() + clazzName.substring(1);
                    singletonObjects.put(beanName, clazz.newInstance());

                } else if (clazz.isAnnotationPresent(Service.class)) {
                    Service annotation = clazz.getAnnotation(Service.class);
                    String beanName;
                    if (annotation != null) {
                        beanName = annotation.value();
                        if (StringUtils.isBlank(beanName)) {
                            // 接口类名
                            Class<?>[] interfaces = clazz.getInterfaces();
                            String simpleName = interfaces[0].getSimpleName();
                            beanName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
                        }
                        singletonObjects.put(beanName, clazz.newInstance());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object autoWired() {
        try {
            for (Map.Entry<String, Object> entry : singletonObjects.entrySet()) {
                Object bean = entry.getValue();
                Field[] declaredFields = bean.getClass().getDeclaredFields();

                // 属性赋值
                for (Field field : declaredFields) {
                    // 这里的注解也可以是java自带的@Resource注解
                    AutoWired annotation = field.getAnnotation(AutoWired.class);
                    if (annotation == null) {
                        continue;
                    }
                    String beanName = annotation.value();
                    Class<?> declaringClass;
                    // @AutoWired注解value没有指定beanName，默认使用field对象首字母小写
                    if (StringUtils.isBlank(beanName)) {
                        declaringClass = field.getType();
                        String declaringClassName = declaringClass.getSimpleName();
                        beanName = declaringClassName.substring(0, 1).toLowerCase() + declaringClassName.substring(1);
                    }
                    field.setAccessible(true);
                    // 为属性赋值（属性名不一定和注入的beanName一致）
                    field.set(bean, singletonObjects.get(beanName));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 扫描包
     *
     */
    public void scanPackage(String pack) {
        // com.zhanss.controller  -->  com/zhanss/controller
        URL url = this.getClass().getClassLoader().getResource("/"+ pack.replaceAll("\\.", "/"));
        if (url == null) {
            return;
        }
        String path = url.getFile();
        File dir = new File(path);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                // 当前是一个文件目录 com.zhanss.controller
                scanPackage(pack+ "."+ file.getName());
            } else {
                // 文件目录下文件 获取全路径
                String className = pack+ "." + file.getName().replaceAll(".class", "");
                classNameList.add(className);
            }
        }

    }

}
