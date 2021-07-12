package com.zhanss.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解：修饰注解的注解
 * @Target 说明当前注解应用的位置
 * @Retention 说明当前注解
 *
 * @Author zhanss
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoWired {

    String value() default "";
}
