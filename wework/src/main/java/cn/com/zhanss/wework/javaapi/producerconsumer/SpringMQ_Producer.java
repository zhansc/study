package cn.com.zhanss.wework.javaapi.producerconsumer;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

/**
 * spring整合ActiveMQ生产者
 *
 * @author zhanss
 * @since 2019/10/11
 */
@Service
public class SpringMQ_Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext act = new ClassPathXmlApplicationContext("config/spring-config.xml");
        SpringMQ_Producer mqProducer = (SpringMQ_Producer) act.getBean("springMQ_Producer");
        mqProducer.jmsTemplate.send(session -> {
            TextMessage textMessage = session.createTextMessage("======springaop-activemq-queue=====");
            return textMessage;
        });

        System.out.println("==========springMQ send over==========");
    }

}
