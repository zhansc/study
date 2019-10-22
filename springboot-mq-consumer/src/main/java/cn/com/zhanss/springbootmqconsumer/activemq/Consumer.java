package cn.com.zhanss.springbootmqconsumer.activemq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * 消费者
 *
 * @author zhanss
 * @since 2019/10/12
 */
@Component
public class Consumer {

    /**
     * 指定消息监听器
     * @param textMessage
     *        消息
     * @throws JMSException
     */
    @JmsListener(destination = "${mytopic}")
    public void consumeTopic(TextMessage textMessage) throws JMSException {
        System.out.println("====主题消费者接收到消息："+ textMessage.getText());
    }

//    @JmsListener(destination = "${myqueue}")
//    public void consumeQueue(TextMessage textMessage) throws JMSException {
//        System.out.println("====队列消费者接收到消息："+ textMessage.getText());
//    }

}
