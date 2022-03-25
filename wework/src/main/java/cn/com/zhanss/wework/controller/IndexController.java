package cn.com.zhanss.wework.controller;

import cn.com.zhanss.wework.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc
 * @author zhanshuchan
 * @date 2021/7/23
 */
@RestController
public class IndexController {

    /**
     * springboot 启动页
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "HELLO SPRING-BOOT";
    }

    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser() {
        return new User(1, "李四", "男", "12345678901",12, 11, "", "", "");
    }

}
