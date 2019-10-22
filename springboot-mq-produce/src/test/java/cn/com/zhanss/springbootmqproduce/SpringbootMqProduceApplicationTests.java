package cn.com.zhanss.springbootmqproduce;

import cn.com.zhanss.springbootmqproduce.activemq.producer.QueueProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMqProduceApplicationTests {

    @Resource
    private QueueProducer queueProducer;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testQueueProducer() {
        // 队列生产者
        queueProducer.queueProduceScheduled();
    }

    @Test
    public void testTopicProducer() {
        // 主题生产者
        queueProducer.topicProduceScheduled();
    }
}
