package com.zhanss.service;

import com.zhanss.entity.User;

/**
 * @author zhanshuchan
 * @desc 服务层-用户
 * @date 2021/6/27
 */
public interface UserService {

    void sayHello();

    User getUser();

    User getUserByName(String name);

}
