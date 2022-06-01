package cn.com.zhanss.wework.controller;

import cn.com.zhanss.wework.entity.User;
import cn.com.zhanss.wework.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @desc
 * @author zhanshuchan
 * @date 2021/7/23
 */
@RestController
@RequestMapping("/user")
public class IndexController {

    @Resource
    private UserService userService;
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

    @RequestMapping("/detail")
    public String detail(@Valid @RequestBody User user) {
        return userService.getUserName(user);
    }
}
