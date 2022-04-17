package cn.com.zhanss.springboot.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义Hbase配置
 *
 * @ConfigurationProperties 注解
 * 增加自定义属性配置需求依赖spring-boot-configuration-processor jar包
 * @author zhanss
 * @since 2022-04-16
 */
@Getter
@Setter
@ToString
@Component
/**
 * 1、将该当前配置bean属性映射到application配置文件
 */
@ConfigurationProperties(prefix = "hbase.config")
/**
 * 指定加载的配置文件
 */
@PropertySource("classpath:application.yaml")
public class HbaseConfigProperties {

    private String ip;

    private String port;

    private Integer maxSize;

    private Integer idleSize;

}
