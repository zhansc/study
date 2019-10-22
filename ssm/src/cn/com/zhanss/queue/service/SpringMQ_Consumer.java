package cn.com.zhanss.queue.service;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * spring整合ActiveMQ消费者
 *
 * @author zhanss
 * @since 2019/10/11
 */
@Service
public class SpringMQ_Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext act = new ClassPathXmlApplicationContext("config/spring-config.xml");
        SpringMQ_Consumer mqConsumer = (SpringMQ_Consumer) act.getBean("springMQ_Consumer");
        // 接收消息并转换为字符串
        String message = (String) mqConsumer.jmsTemplate.receiveAndConvert();

        System.out.println("======消费者："+ message);
    }

}
