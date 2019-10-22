package cn.com.zhanss.queue.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 队列生产者
 *
 * @author zhanss
 * @since 2019/9/24
 */
public class JmsProducer {
    // 连接linux服务器上的ActiveMQ
//    private static final String ACTIVE_URL ="tcp://192.168.0.132:61616";
    // 连接本地Java应用嵌入的ActiveMQ实例broker
    private static final String ACTIVE_URL ="tcp://localhost:61616";
    private static final String QUEUE_NAME ="queue_1";

    public static void main (String[] args) throws JMSException {
        // 用户名、密码没传，默认为admin/admin
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVE_URL);
        // 创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // 创建会话：第一个参数是事务，第二个参数是签收
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 创建目的地：队列或主题，并指定名称
        Queue queue = session.createQueue(QUEUE_NAME);
        // 将目的地传给消息生产者
        MessageProducer messageProducer = session.createProducer(queue);

        for (int i = 0; i < 3; i ++) {
            // 会话创建字符串消息
            TextMessage textMessage = session.createTextMessage(QUEUE_NAME + "_messageListener_" + i);
            // 消息生产者发送
            messageProducer.send(textMessage);
        }
        try {
            // 事务提交
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 异常情况，回滚
            session.rollback();
        } finally {
            // 资源关闭顺序和创建顺序倒过来
            messageProducer.close();
            session.close();
            connection.close();
        }

        System.out.println("*******消息创建完成********");
    }

}
