package cn.com.zhanss.springboot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

/**
 * @author zhanss
 * @date
 **/
@Data
@PropertySource(value = "classpath:/my/loadEnum.yaml")
@ConfigurationProperties(prefix = "my")
public class MyConfig {
    private Map<String, String> fieldMap;

    private List<String> fieldList;

    @Value("${ring.alarm}")
    private String ringAlarm;

}
