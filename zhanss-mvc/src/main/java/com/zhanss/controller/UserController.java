package com.zhanss.controller;

import com.zhanss.entity.User;
import com.zhanss.framework.annotation.*;
import com.zhanss.service.UserService;

/**
 * @desc 控制层-用户
 * @author zhanshuchan
 * @date 2021/6/27
 */
@Controller
public class UserController {

    @AutoWired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
//        userService.sayHello();
        return "success.jsp";
    }

    @RequestMapping("/sayHello")
    public String success() {
        return "success";
    }

    @RequestMapping("/getUser")
    @ReponseBody // 返回json数据
    public User getUser() {
        return userService.getUser();
    }

    @RequestMapping("/getUserByName")
    @ReponseBody
    public User getUserByName(@RequestParam String name) {
        return userService.getUserByName(name);
    }
}
