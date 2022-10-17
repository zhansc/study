package cn.com.zhanss.springboot.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启动班长
 *
 * @author zhanss
 * @since 2022-10-13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Import(MonitorService.class)
public @interface EnableMonitor {

}
