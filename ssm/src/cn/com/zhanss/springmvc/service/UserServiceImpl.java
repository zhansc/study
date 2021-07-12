package cn.com.zhanss.springmvc.service;

import org.springframework.stereotype.Service;

/**
 * @desc 用户
 * @author zhanshuchan
 * @date 2021/6/29
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void sayHello() {
        System.out.println("say hello!!");
    }
}
