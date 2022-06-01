package cn.com.zhanss.wework.service.impl;

import cn.com.zhanss.wework.entity.User;
import cn.com.zhanss.wework.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * 用户
 *
 * @author zhanss
 * @since 2022-05-21
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserName(User user) {
        return user.getName();
    }
}
