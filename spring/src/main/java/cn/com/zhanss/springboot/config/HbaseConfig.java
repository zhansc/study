package cn.com.zhanss.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Hbase配置
 *
 * @author zhanss
 * @since 2022-04-16
 */
@Component
public class HbaseConfig {

    @Bean
    /**
     * 2、将配置文件中的hbase.config相应属性值初始化到Hbase属性
     */
    @ConfigurationProperties(prefix = "hbase.config")
    public Hbase hbase() {
        return new Hbase();
    }
}
