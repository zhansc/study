package cn.com.zhanss.wework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @desc 启动类
 * @author zhanshuchan
 * @date 2021/7/22
 */
@SpringBootApplication(scanBasePackages = {"cn.com.zhanss.wework"})
public class WeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeworkApplication.class, args);
    }
}
