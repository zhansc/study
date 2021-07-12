package com.zhanss.service.impl;

import com.zhanss.entity.User;
import com.zhanss.framework.annotation.Service;
import com.zhanss.service.UserService;

/**
 * @desc 服务层
 * @author zhanshuchan
 * @date 2021/6/27
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void sayHello() {
        System.out.println("say Hello");
    }

    @Override
    public User getUser() {
        return new User(1, "老王", "男");
    }

    @Override
    public User getUserByName(String name) {
        return new User(name.length(), name, name.length()/2 == 0 ? "女" : "男");
    }
}
