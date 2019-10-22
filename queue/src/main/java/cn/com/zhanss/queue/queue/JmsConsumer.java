package cn.com.zhanss.queue.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 消费者
 *
 * @author zhanss
 * @since 2019/9/24
 */
public class JmsConsumer {

    private static final String ACTIVE_URL ="tcp://192.168.0.132:61616";
    private static final String QUEUE_NAME ="queue_1";

    public static void main(String[] args) throws JMSException {

        System.out.println("******我是second消费者******");

        // 用户名、密码没传，默认为admin/admin
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVE_URL);
        // 创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // 创建会话：第一个参数是事务，第二个参数是签收
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 创建目的地：队列或主题，并指定名称
        Queue queue = session.createQueue(QUEUE_NAME);

        //创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        while (true) {
            // 不传参数表示一直等待消息；传timeout表示等待时间；receiveNoWait方法表示不等待
            TextMessage message = (TextMessage) messageConsumer.receive(4000L);
            if(null != message) {
                System.out.println("******消费者"+ message.getText());
                // 消费端手动签收CLIENT_ACKNOWLEDGE，就会提交，若没有签收会重复消费
//                message.acknowledge();
            } else {
                break;
            }
        }

        // 异步阻塞方式：消费者监听器MessageListener
        /*messageConsumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage) message;
            if(null != textMessage) {
                try {
                    System.out.println("******消费者"+ textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        // 阻塞，避免线程结束
        System.in.read();*/
        messageConsumer.close();
        session.close();
        connection.close();
    }

}
