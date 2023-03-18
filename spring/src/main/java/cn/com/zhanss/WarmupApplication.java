package cn.com.zhanss;

import cn.com.zhanss.spring.entity.Monitor;
import cn.com.zhanss.springboot.config.HbaseConfigProperties;
import cn.com.zhanss.springboot.config.MyConfig;
import cn.com.zhanss.springboot.enable.EnableMonitor;
import cn.com.zhanss.springboot.myimport.StudentImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;


/**
 * 主配置类
 *
 * @author zhanss
 * @since 2022-04-16
 */
@EnableConfigurationProperties({HbaseConfigProperties.class, MyConfig.class})
@SpringBootApplication
@Import(value = {StudentImportSelector.class })
@EnableMonitor
public class WarmupApplication {

    public static void main(String[] args) {
        // SpringBoot启动就是IoC容器初始化的过程
        ApplicationContext run = SpringApplication.run(WarmupApplication.class, args);
        System.out.println(run.getBean(Monitor.class));
    }

}
