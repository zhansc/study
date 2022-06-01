package cn.com.zhanss.wework.service;

import cn.com.zhanss.wework.entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author zhanss
 * @since 2022-05-21
 */
public interface UserService {

    String getUserName(User user);

}