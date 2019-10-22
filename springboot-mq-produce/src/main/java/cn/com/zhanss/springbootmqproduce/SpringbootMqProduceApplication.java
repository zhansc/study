package cn.com.zhanss.springbootmqproduce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhanss
 */
@SpringBootApplication
@EnableScheduling
public class SpringbootMqProduceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMqProduceApplication.class, args);
    }

}
