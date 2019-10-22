package cn.com.zhanss.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Callable;

/**
 * 消费者
 *
 @author zhanss
 @since 2019/9/24
 */
public class CustomerProducer {

    public static void main(String[] args) {

        Properties props = new Properties();
        // 将消费者的consumer_offset存放在leader中（而不是zookeeper中，为了减少通信，提高效率）
        props.put("bootstrap.servers", "192.168.0.131:9092");
        // ack机制：0 不等Leader和Follower的响应，直接进行下一条消息的生产；1 等Leader的响应；all 等Leader和Follower所有的响应
        props.put("acks", "all");
        // 重试次数
        props.put("retries", 0);
        // 单次批量发送的量
        props.put("batch.size", 16384);
        // 单次发送的延迟
        props.put("linger.ms", 1);
        // 本地缓存的量
        props.put("buffer.memory", 33554432);
        // key-value的序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 自定义分区：partitioner.class
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "cn.com.zhanss.kafka.producer.CustomerPartitioner");

        // 添加拦截器
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, Arrays.asList("自定义拦截器全类名"));

        // 创建一个生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);
        // 循环发送数据
        for(int i = 0; i < 10; i ++) {
            kafkaProducer.send(new ProducerRecord<>("first", String.valueOf(i)), new Callback() {
                // 发送的回调方法
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception != null || StringUtils.isEmpty(metadata)) {
                        System.out.println("发送失败！");
                    } else {
                        System.out.println(metadata.partition() + "--" +metadata.offset());
                    }
                }
            });

        }
        // 关闭资源
        kafkaProducer.close();
    }

}
