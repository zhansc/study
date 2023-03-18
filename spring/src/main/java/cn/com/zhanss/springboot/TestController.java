package cn.com.zhanss.springboot;

import cn.com.zhanss.springboot.config.Hbase;
import cn.com.zhanss.springboot.config.HbaseConfig;
import cn.com.zhanss.springboot.config.MyConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试
 *
 * @author zhanss
 * @since 2022-04-16
 */
@RestController
public class TestController {

    @Resource
    private HbaseConfig hbaseConfig;
    @Resource
    private MyConfig myConfig;

    @RequestMapping("/test")
    public String sayHello() {
        System.out.println("hello");
        Hbase hbase = hbaseConfig.hbase();
        System.out.println("hello"+ hbase);
        return "hello";
    }

    @GetMapping("/myconfig")
    public String myConfig() {
        String ringAlarm = myConfig.getRingAlarm();
        return ringAlarm;
    }
}
