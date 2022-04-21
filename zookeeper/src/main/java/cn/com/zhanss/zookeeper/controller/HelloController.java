package cn.com.zhanss.zookeeper.controller;

import cn.com.zhanss.zookeeper.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;

/**
 * Hello
 *
 * @author zhanss
 * @since 2022-04-21
 */
@Controller
public class HelloController {

    @Reference
    private HelloService helloService;

    public String sayHello() {
        return helloService.sayHello("zhangsan");
    }
}

