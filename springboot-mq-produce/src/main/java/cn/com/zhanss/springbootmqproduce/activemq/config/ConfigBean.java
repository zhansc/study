package cn.com.zhanss.springbootmqproduce.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 定义Bean配置，相当于spring中的ApplicationContext.xml配置文件
 *
 * @author zhanss
 * @since 2019/10/12
 */
@Component
@EnableJms
public class ConfigBean {

    /**
     * 自定义队列名称
     */
    @Value("${myqueue}")
    private String myQueue;

    @Value("${mytopic}")
    private String myTopic;

    /**
     * 这个方法相当于spring中的一个bean配置：<bean id="queue" class=""/>
     * @return
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(myQueue);
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(myTopic);
    }

}