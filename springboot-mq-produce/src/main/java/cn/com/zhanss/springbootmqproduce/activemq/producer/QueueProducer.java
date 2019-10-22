package cn.com.zhanss.springbootmqproduce.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.UUID;

/**
 * 消息生产者
 *
 * @author zhanss
 * @since 2019/10/12
 */
@Component
// 解决跨域问题
//@CrossOrigin
public class QueueProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    public void produce() {

        jmsMessagingTemplate.convertAndSend(queue, "======生产消息："+ UUID.randomUUID().toString().substring(6));
    }

    @Scheduled(fixedDelay = 3000)
    public void queueProduceScheduled() {

        jmsMessagingTemplate.convertAndSend(queue, "======scheduled生产队列消息："+ UUID.randomUUID().toString().substring(6));
        System.out.println("*****schedule send queue message*****");
    }

    @Scheduled(fixedDelay = 3000)
    public void topicProduceScheduled() {

        jmsMessagingTemplate.convertAndSend(topic, "======scheduled生产主题消息："+ UUID.randomUUID().toString().substring(6));
        System.out.println("*****schedule send topic message*****");
    }

}
