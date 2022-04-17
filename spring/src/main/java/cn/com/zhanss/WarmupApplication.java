package cn.com.zhanss;

import cn.com.zhanss.springboot.config.HbaseConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 主配置类
 *
 * @author zhanss
 * @since 2022-04-16
 */
@EnableConfigurationProperties(HbaseConfigProperties.class)
@SpringBootApplication
public class WarmupApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarmupApplication.class, args);
    }

}
