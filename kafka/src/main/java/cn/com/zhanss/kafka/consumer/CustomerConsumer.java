package cn.com.zhanss.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Properties;

/**
 * 消费者
 *
 * @author zhanss
 * @since 2019/9/24
 */
public class CustomerConsumer {
    
    public static void main(String[] args) {

        Properties props = new Properties();
        // kafka集群
        props.put("bootstrap.servers", "192.168.0.131:9092");
        // 消费者组ID
        props.put("group.id", "zhanss");
        // 自动提交：offset
        props.put("enable.auto.commit", "false");
        // 自动提交的延迟时间：低级API处理，保证消息完全处理完成，后再发送offset，保持一致（若太快，可能会在消息未消费完成服务异常了，但offset已经发送导致数据遗漏或消息消费完成offset未发送等导致重复消费）
        props.put("auto.commit.interval.ms", "1000");
        // key-value反序列化
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        // 指定topic
        kafkaConsumer.subscribe(Arrays.asList("first", "second"));
        // 指定消费的offset（重复消费），下一次poll()获取的是最新的偏移量offset，任意使用此API会导致数据丢失
        kafkaConsumer.seek(new TopicPartition("first", 0), 2);
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for(ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.topic()+ "--"+ consumerRecord.partition()+ "--" +consumerRecord.value());
                System.out.println(consumerRecord.topic()+ "--"+ consumerRecord.partition()+ "--" +consumerRecord.value());
            }
        }
    }
    
}
