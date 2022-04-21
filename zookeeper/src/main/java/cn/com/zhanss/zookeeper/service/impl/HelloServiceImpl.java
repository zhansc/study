package cn.com.zhanss.zookeeper.service.impl;

import cn.com.zhanss.zookeeper.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * Hello
 *
 * @author zhanss
 * @since 2022-04-21
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String say) {
        System.out.println("say hello!!!");
        return say + "Hello";
    }

}
