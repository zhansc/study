package cn.com.zhanss.queue.queue;

/**
 * 消费者
 *
 * @author zhanss
 * @since 2019/9/24
 */
public class JmsConsumer_Topic {

    private static final String ACTIVE_URL ="tcp://192.168.0.132:61616";
    private static final String TOPIC_NAME ="topic_1";

    public static void main(String[] args) throws JMSException {

        System.out.println("******我是second消费者******");

        // 用户名、密码没传，默认为admin/admin
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVE_URL);
        // 创建连接
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("zhan2");

        // 创建会话：第一个参数是事务，第二个参数是签收
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 创建目的地：队列或主题，并指定名称
        Topic topic = session.createTopic(TOPIC_NAME);

        //创建消费者
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark...");
        connection.start();
        while (true) {
            // 不传参数表示一直等待消息；传timeout表示等待时间；receiveNoWait方法表示不等待
            TextMessage message = (TextMessage) topicSubscriber.receive(4000L);
            if(null != message) {
                System.out.println("******消费者"+ message.getText());
                // 消费端手动签收CLIENT_ACKNOWLEDGE，就会提交，若没有签收会重复消费

            } else {
                break;
            }
        }

        topicSubscriber.close();
        session.close();
        connection.close();
    }

}
