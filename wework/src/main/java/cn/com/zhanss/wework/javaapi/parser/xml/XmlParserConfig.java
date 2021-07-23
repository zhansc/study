package cn.com.zhanss.wework.javaapi.parser.xml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @desc xml解析器
 * @author zhanshuchan
 * @date 2021/7/20
 */
//@ImportResource("classpath*:application.properties")
@Configuration
public class XmlParserConfig {

    @Bean
    public XmlParser xmlParserClient() {
        return new XmlParser();
    }

}
